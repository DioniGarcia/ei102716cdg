package es.uji.ei102716cdg.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uji.ei102716cdg.dao.CollaborationDao;
import es.uji.ei102716cdg.dao.OfferDao;
import es.uji.ei102716cdg.dao.RequestDao;
import es.uji.ei102716cdg.dao.SkillDao;
import es.uji.ei102716cdg.dao.StudentDao;
import es.uji.ei102716cdg.domain.collaboration.Collaboration;
import es.uji.ei102716cdg.domain.collaboration.Offer;
import es.uji.ei102716cdg.domain.collaboration.Post;
import es.uji.ei102716cdg.domain.collaboration.Request;
import es.uji.ei102716cdg.domain.skill.Skill;
import es.uji.ei102716cdg.domain.user.Student;
import es.uji.ei102716cdg.domain.user.User;

@Service
public class PostService implements PostServiceInterface {

	@Autowired
	StudentDao studentDao;
	
	@Autowired
	SkillDao skillDao;
	
	@Autowired
	OfferDao offerDao;
	
	@Autowired
	RequestDao requestDao;
	
	@Autowired
	CollaborationDao collaborationDao;
	
	@Override
	public List<String> getActiveStudentsNick() {
		
		List<String> nickList = new ArrayList<>();
		
		for(Student student : studentDao.getStudents()){
			nickList.add(student.getNick());
		}

		return nickList;
	}

	@Override
	public List<Skill> getActiveSkills() {
		
		List<Skill> skillList = new ArrayList<>();
		for(Skill skill : skillDao.getSkills()){
			if(skill.isActive()){
				skillList.add(skill);
			}
		}
		return skillList;
	}
	
	@Override
	public List<Skill> getActiveSkills(int number) {
		
		List<Skill> skillList = new ArrayList<>();
		
		for(Skill skill : skillDao.getSkills()){
			if(skill.isActive()){
				skillList.add(skill);
			}
			if (skillList.size() == number) {
				break;
			}
		}
		return skillList;
	}
	
	@Override
	public List<Offer> getActiveOffers() {
		List<Offer> offerList = new ArrayList<>();
		for(Offer offer : offerDao.getOffers()){
			if(offer.isActive()){
				offerList.add(offer);
			}
		}
		return offerList;
	}
	
	@Override
	public List<Request> getActiveRequests() {
		List<Request> requestList = new ArrayList<>();
		for(Request request : requestDao.getRequests()){
			if(request.isActive()){
				requestList.add(request);
			}
		}
		return requestList;
	}

	@Override
	public List<Skill> getSkillsByPost(List<? extends Post> list) {
		List<Skill> listSkill = new ArrayList<>();
		for (Post post : list){
			listSkill.add(skillDao.getSkill(post.getSkill_Id()));
		}
		
		return listSkill;
	}
	
	@Override
	public List<User> getUsersByPost(List<? extends Post> list) {
		List<User> listUser = new ArrayList<>();
		for (Post post : list){
			listUser.add(studentDao.getStudent(post.getStudent_nick()));
		}
		return listUser;
	}
	
	@Override
	public List<Student> getStudentsByPost(List<? extends Post> list) {
		List<Student> listUser = new ArrayList<>();
		for (Post post : list){
			listUser.add(studentDao.getStudent(post.getStudent_nick()));
		}
		return listUser;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Offer> getRecentOffers(){
		
		List<Offer> list = offerDao.getOffers(); 
		list = (List<Offer>) sortPostByStartDate(list);
		
		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Offer> getActiveRecentOffers(int number , String nick) {
		List<Offer> offerList = new ArrayList<>();
		
		Date today = new java.sql.Date(new java.util.Date().getTime());
		
		for(Offer offer : offerDao.getOffers()){
			if(offer.isActive()){
				if(offer.getEndDate().after(today)){	
					if(!offer.getStudent_nick().equals(nick)){
						offerList.add(offer);
					}
				}
				else {
					offer.setActive(false);
					offerDao.updateOffer(offer);
				}
			}
			
			if(number > 0 && offerList.size() == number){
				break;
			}
		}
		return (List<Offer>) sortPostByStartDate(offerList);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Offer> getMyOffers(String nick) {
		List<Offer> offerList = new ArrayList<>();
		
		Date today = new java.sql.Date(new java.util.Date().getTime());
		
		for(Offer offer : offerDao.getOffersByNick(nick)){
			if(offer.isActive()){
				if(offer.getEndDate().after(today)){	
					offerList.add(offer);
				}
				else {
					offer.setActive(false);
					offerDao.updateOffer(offer);
				}
			}
		}
		return (List<Offer>) sortPostByStartDate(offerList);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Request> getActiveRecentRequests(int number, String nick) {
		List<Request> requestList = new ArrayList<>();
		
		Date today = new java.sql.Date(new java.util.Date().getTime());
		
		for(Request request : requestDao.getRequests()){
			if(request.isActive()){
				if(request.getEndDate().after(today)){	
					if(!request.getStudent_nick().equals(nick)){
						requestList.add(request);
					}
				}
				else {
					request.setActive(false);
					requestDao.updateRequest(request);
				}
			}
			
			if(number > 0 && requestList.size() == number){
				break;
			}
		}
		return (List<Request>) sortPostByStartDate(requestList);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Request> getMyRequests(String nick) {
		List<Request> requestList = new ArrayList<>();
		
		Date today = new java.sql.Date(new java.util.Date().getTime());
		
		for(Request request : requestDao.getRequestsByNick(nick)){
			if(request.isActive()){
				if(request.getEndDate().after(today)){						
					requestList.add(request);
				}
				else {
					request.setActive(false);
					requestDao.updateRequest(request);
				}
			}
		}
		return (List<Request>) sortPostByStartDate(requestList);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Request> getRecentRequests(){
		
		List<Request> list = requestDao.getRequests(); 
		list = (List<Request>) sortPostByStartDate(list);
		
		return list;
	}
	
	
	/**Ordena una lista de post, las más recientes primero
	 * 
	 * @param 	list: Lista de Posts (Ofertas o Demandas)
	 * @return	lista ordenada de más a menos reciente
	 */
	private List<? extends Post> sortPostByStartDate(List<? extends Post> list){
		Comparator<Post> comparatorPost =  (p1, p2) ->  (p1).getStartDate().compareTo(p2.getStartDate());				
		
		list.sort(comparatorPost);
		return list;
	}
	
	@Override
	public Skill getSkillById(int id){
		return skillDao.getSkill(id);
	}
	
	public Skill getSkillById(List<Skill> skills, int id){
		for (Skill skill : skills){
			if (skill.getSkill_id() == id)
				return skill;
		}
		return null;
	}
	
	@Override
	public Student getStudentByNick(String nick){
		return studentDao.getStudent(nick);
	}

	@Override
	public List<Request> getRequestsBySkillId(String nick, int skillId){
		List<Request> requestBySkill = new ArrayList<>();
		for (Request req : requestDao.getRequestsByNick(nick)){
			if (req.getSkill_Id() == skillId)
				requestBySkill.add(req);
		}
		return requestBySkill;
	}

	@Override
	public List<Offer> getOffersBySkillId(String nick, int skillId) {
		List<Offer> offerBySkill = new ArrayList<>();
		for (Offer off : offerDao.getOffersByNick(nick)){
			if (off.getSkill_Id() == skillId)
				offerBySkill.add(off);
		}
		return offerBySkill;
	}

	@Override
	public void addCollaboration(int offerId, int requestId) {
		collaborationDao.addCollaboration(new Collaboration(offerId, requestId));
	}
	
	@Override
	public int addRequestAndGetId(Request request){
		return requestDao.addRequestAndGetId(request);
	}
	
	@Override
	public int addOfferAndGetId(Offer offer){
		return offerDao.addOfferAndGetId(offer);
	}

	@Override
	public Offer getOffer(int id) {
		
		return offerDao.getOffer(id);
	}
	
	
	public Offer getOffer(List<Offer> offers, int id) {
		for (Offer offer : offers)
			if (offer.getId() == id)
				return offer;
		return null;
	}

	@Override
	public Request getRequest(int id) {
		return requestDao.getRequest(id);
	}
	
	
	public Request getRequest(List<Request> requests, int id) {
		for (Request request : requests)
			if (request.getId() == id)
				return request;
		return null;
	}

	@Override
	public List<Collaboration> getCollaborations(String nick) {
		return collaborationDao.getAllCollaborationsByNick(nick);
	}

	@Override
	public List<Collaboration> getActiveCollaborations(String nick) {
		return collaborationDao.getActiveCollaborationsByNick(nick);
	}

	@Override
	public List<Collaboration> getOldCollaborations(String nick) {
		return collaborationDao.getOldCollaborationsByNick(nick);
	}

	@Override
	public List<Collaboration> getEvalCollaborations(String nick) {
		return collaborationDao.getEvalCollaborationsByNick(nick);
	}

	@Override
	public List<Skill> getSkillsByCollabs(List<Collaboration> collabs) {
		List<Skill> skills = new ArrayList<>();
		for (Collaboration col : collabs){
			skills.add(getSkillById(getOffer(col.getOffer_id()).getSkill_Id()));
		}
		return skills;
	}

	@Override
	public List<String> getCollabEndDates(List<Collaboration> collabs) {
		List<String> dates = new ArrayList<>();
		for (Collaboration col : collabs){
			Offer offer = offerDao.getOffer(col.getOffer_id());
			Request request = requestDao.getRequest(col.getRequest_id());
			if (offer.getEndDate().before(request.getEndDate()))
				dates.add(offer.getEndDate().toString());
			else
				dates.add(request.getEndDate().toString());
		}
		return dates;
	}

	@Override
	public List<Offer> getPaginatedOffers(List<Offer> offers, int pageSize, int page, String nick) {
		int indice = page-1;
		List<Offer> pagedOffers = new ArrayList<>();
		for (int i = indice*pageSize; i < offers.size() && i< indice+pageSize; i++){
			pagedOffers.add(offers.get(i));
		}
		return pagedOffers;
		
	}

	@Override
	public List<Request> getPaginatedRequests(List<Request> requests, int pageSize, int page, String nick) {
		int indice = page-1;
		List<Request> ret = new ArrayList<>();
		for (int i = indice*pageSize; i < requests.size() && i< indice+pageSize; i++){
			ret.add(requests.get(i));
		}
		return ret;
	}

	@Override
	public int getOffersPageCount(List<Offer> offers, int pageElements, String nick) {
		return (offers.size() + pageElements - 1) / pageElements; // ceil the division offers.size / size
	}

	@Override
	public int getRequestsPageCount(List<Request> requests, int pageElements, String nick) {
		return (requests.size() + pageElements - 1) / pageElements; // ceil the division requests.size / size
	}

	@Override
	public int getRating(String nick){
		List<Collaboration> collabs = getCollaborations(nick);
		int suma = 0, n = 0;
		
		for (Collaboration collab : collabs){
			Offer offer = getOffer(collab.getOffer_id());
			Request request = getRequest(collab.getRequest_id());
			String status = collab.getStatus(offer.getEndDate(), request.getEndDate());
			if (offer.getStudent_nick().equals(nick) && status=="finished"){
				suma += collab.getRating();
				n++;
			}
		}
		
		return n==0 ? 0 : suma/n;
	}
		
	@Override
	public List<Integer> getRatingByStudents(List<Student> students){
		List<Integer> ratings = new ArrayList<Integer>();
		for (Student student : students){
			ratings.add(getRating(student.getNick()));
		}
		return ratings;
	}
	
	private Student getCollabStudentOf(Collaboration collab){
		Offer offer = getOffer(collab.getOffer_id());
		return getStudentByNick(offer.getStudent_nick());
	}
	
	private Student getCollabStudentRq(Collaboration collab){
		Request request = getRequest(collab.getRequest_id());
		return getStudentByNick(request.getStudent_nick());
	}
	
	@Override
	public List<Student> getStudentsByCollabsOf(List<Collaboration> collabs){
		List<Student> students = new ArrayList<Student>();
		for (Collaboration collab : collabs){
			students.add(getCollabStudentOf(collab));
		}
		return students;
	}
	
	@Override
	public List<Student> getStudentsByCollabsRq(List<Collaboration> collabs){
		List<Student> students = new ArrayList<Student>();
		for (Collaboration collab : collabs){
			students.add(getCollabStudentRq(collab));
		}
		return students;
	}

	@Override
	public List<Offer> searchOffers(String query, String nick) {
		return offerDao.searchOffers(query, nick);
	}

	@Override
	public List<Request> searchRequests(String query, String nick) {
		return requestDao.searchRequests(query, nick);
	}
}
