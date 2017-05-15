package es.uji.ei102716cdg.service;

import java.util.ArrayList;
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
		
		List<String> nickList = new ArrayList<String>();
		
		for(Student student : studentDao.getStudents()){
			nickList.add(student.getNick());
		}

		return nickList;
	}

	@Override
	public List<Skill> getActiveSkills() {
		
		List<Skill> skillList = new ArrayList<Skill>();
		for(Skill skill : skillDao.getSkills()){
			if(skill.isActive()){
				skillList.add(skill);
			}
		}
		return skillList;
	}
	
	@Override
	public List<Offer> getActiveOffers() {
		List<Offer> offerList = new ArrayList<Offer>();
		for(Offer offer : offerDao.getOffers()){
			if(offer.isActive()){
				offerList.add(offer);
			}
		}
		return offerList;
	}

	@Override
	public List<Request> getActiveRequests() {
		List<Request> requestList = new ArrayList<Request>();
		for(Request request : requestDao.getRequests()){
			if(request.isActive()){
				requestList.add(request);
			}
		}
		return requestList;
	}

	@Override
	public List<Chat> getChats() {
		List<Chat> chatList = new ArrayList<Chat>();
		
		for(Chat chat : chatDao.getChats()){
			chatList.add(chat);
		}
		return chatList;
	}

	@Override
	public List<Skill> getSkillsByOffers(List<Offer> list) {
		List<Skill> listSkill = new ArrayList<Skill>();
		for (Offer offer : list){
			listSkill.add(skillDao.getSkill(offer.getSkill_Id()));
		}
		
		return listSkill;
	}
	
	@Override
	public List<User> getUsersByOffers(List<Offer> list) {
		List<User> listUser = new ArrayList<User>();
		for (Offer offer : list){
			listUser.add(studentDao.getStudent(offer.getStudent_nick()));
		}
		return listUser;
	}
	@Override
	public List<Offer> getRecentOffers(){
		return offerDao.getRecentOffers();
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
		List<Request> requestBySkill = new ArrayList<Request>();
		for (Request req : requestDao.getRequestsByNick(nick)){
			if (req.getSkill_Id() == skillId)
				requestBySkill.add(req);
		}
		return requestBySkill;
	}

	@Override
	public List<Offer> getOffersBySkillId(String nick, int skillId) {
		List<Offer> offerBySkill = new ArrayList<Offer>();
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

	@Override
	public Request getRequest(int id) {
		return requestDao.getRequest(id);
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
		List<Skill> skills = new ArrayList<Skill>();
		for (Collaboration col : collabs){
			skills.add(getSkillById(getOffer(col.getOffer_id()).getSkill_Id()));
		}
		return skills;
	}

	@Override
	public List<String> getCollabEndDates(List<Collaboration> collabs) {
		List<String> dates = new ArrayList<String>();
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
	

}
