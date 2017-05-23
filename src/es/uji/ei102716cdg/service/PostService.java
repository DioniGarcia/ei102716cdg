package es.uji.ei102716cdg.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uji.ei102716cdg.dao.BanDao;
import es.uji.ei102716cdg.dao.ChatDao;
import es.uji.ei102716cdg.dao.CollaborationDao;
import es.uji.ei102716cdg.dao.OfferDao;
import es.uji.ei102716cdg.dao.RequestDao;
import es.uji.ei102716cdg.dao.SkillDao;
import es.uji.ei102716cdg.dao.StudentDao;
import es.uji.ei102716cdg.domain.chat.Chat;
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
	BanDao banDao;
	
	@Autowired
	ChatDao chatDao;
	
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
	public List<Chat> getChats() {
		List<Chat> chatList = new ArrayList<>();
		
		for(Chat chat : chatDao.getChats()){
			chatList.add(chat);
		}
		return chatList;
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
		
		Date today = new Date();
		
		for(Offer offer : offerDao.getOffers()){
			if(offer.getEndDate().after(today) && offer.isActive() && !offer.getStudent_nick().equals(nick)){
				offerList.add(offer);
			}
			if(offerList.size() == number){
				break;
			}
		}
		return (List<Offer>) sortPostByStartDate(offerList);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Request> getActiveRecentRequests(int number, String nick) {
		List<Request> requestList = new ArrayList<>();
		
		Date today = new Date();
		
		for(Request request : requestDao.getRequests()){
			if(request.getEndDate().after(today) && request.isActive() && !request.getStudent_nick().equals(nick)){
				requestList.add(request);
			}
			if(requestList.size() == number){
				break;
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
		Comparator<Post> comparatorPost =  (p1, p2) ->  (p2).getStartDate().compareTo(p1.getStartDate());				
		
		list.sort(comparatorPost);
		return list;
	}
	
	@Override
	public Skill getSkillById(int id){
		return skillDao.getSkill(id);
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
	public List<Offer> getPaginatedOffers(int pageSize, int page) {
		int indice = page-1;
		List<Offer> offers = offerDao.getOffers();
		List<Offer> ret = new ArrayList<>();
		for (int i = indice*pageSize; i < offers.size() && i< indice+pageSize; i++){
			ret.add(offers.get(i));
		}
		return ret;
	}

	@Override
	public List<Request> getPaginatedRequests(int pageSize, int page) {
		int indice = page-1;
		List<Request> requests = requestDao.getRequests();
		List<Request> ret = new ArrayList<>();
		for (int i = indice*pageSize; i < requests.size() && i< indice+pageSize; i++){
			ret.add(requests.get(i));
		}
		return ret;
	}

	@Override
	public int getOffersPageCount(int size) {
		return (offerDao.getOffers().size() + size - 1) / size; // ceil the division offers.size / size
	}

	@Override
	public int getRequestsPageCount(int size) {
		return (requestDao.getRequests().size() + size - 1) / size; // ceil the division requests.size / size
	}

	@Override
	public int getUserPoints(String nick) {
		int sum = 20;
		Offer offer = null;
		List<Collaboration> collabs = collaborationDao.getAllCollaborationsByNick(nick);
		for (Collaboration col : collabs){
			offer = offerDao.getOffer(col.getOffer_id());
			
			short totalHours = col.getTotalHours();
			
			if ( offer.getStudent_nick().equals(nick)){
				short rating = col.getRating();
				if (rating >= 3)
					sum += totalHours * Math.pow(1.12,  rating - 3f); // factor beneficioso 
				else
					sum += totalHours * Math.pow(0.88,  3f - rating); // factor penalizador
			
			} else { // demandante
				sum -= totalHours;
			}
		}
		
		sum += offerDao.getOffersByNick(nick).size() * 2;
		
		return sum;
	}

	@Override
	public int getReceivedHours(String nick) {
		
		int sum = 0;
		
		Request request = null;
		List<Collaboration> collabs = collaborationDao.getAllCollaborationsByNick(nick);
		for (Collaboration col : collabs){
			request = requestDao.getRequest(col.getRequest_id());
			
			if (  request.getStudent_nick().equals(nick)){
				sum += col.getTotalHours();
			} 
		}
		
		return sum;
	}

	@Override
	public int getOfferedHours(String nick) {
		int sum = 0;
		
		Offer offer = null;
		List<Collaboration> collabs = collaborationDao.getAllCollaborationsByNick(nick);
		for (Collaboration col : collabs){
			offer = offerDao.getOffer(col.getOffer_id());
			
			if ( offer.getStudent_nick().equals(nick)){
				sum += col.getTotalHours();
			} 
		}
		
		return sum;
	}

	@Override
	public int[] getPostStats() {
		int[] ret = new int[2];
		ret[0] = offerDao.getOffers().size();
		ret[1] = requestDao.getRequests().size();
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * @see es.uji.ei102716cdg.service.PostServiceInterface#getGeneralStats()
	 */
	
	@Override
	public int[][] getGeneralStats() {
		int[][] matrizStatsPorMeses = new int[3][11];
		String[] meses = 	{"Septiembre", "Octubre", "Noviembre", "Diciembre",
							 "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio"};
		int[] mesesInt = 	{8, 9, 10, 11,
				 0, 1, 2, 3, 4, 5, 6};
		List<Offer> offers = offerDao.getOffers();
		List<Request> requests = requestDao.getRequests();
		List<Collaboration> collabs = collaborationDao.getCollaborations();
		
		List<Offer> temp = new ArrayList<Offer>();
		for (int i = 0 ; i < 11 ; i++){
			for (Offer offer : offers){
				if (offer.getStartDate().getMonth() == mesesInt[i])
					temp.add(offer);
			}
			matrizStatsPorMeses[0][i] = temp.size();
			temp.clear();
		}
		
		List<Collaboration> temp2 = new ArrayList<Collaboration>();
		for (int i = 0 ; i < 11 ; i++){
			for (Collaboration collaboration : collabs){
				Date offerDate = this.getOffer(offers,collaboration.getOffer_id()).getStartDate();
				Date requestDate = this.getRequest(requests,collaboration.getRequest_id()).getStartDate();
				if (offerDate.after(requestDate)){
					if (offerDate.getMonth() == mesesInt[i])
						temp2.add(collaboration);
				} else {
					if (requestDate.getMonth() == mesesInt[i])
						temp2.add(collaboration);
				}
			}
			matrizStatsPorMeses[1][i] = temp2.size();
			temp2.clear();
		}
		
		List<Request> temp3 = new ArrayList<Request>();
		for (int i = 0 ; i < 11 ; i++){
			for (Request request : requests){
				if (request.getStartDate().getMonth() == mesesInt[i])
					temp3.add(request);
			}
			matrizStatsPorMeses[2][i] = temp3.size();
			temp3.clear();
		}
		return matrizStatsPorMeses;
	}

	
	

}
