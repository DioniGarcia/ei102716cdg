package es.uji.ei102716cdg.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uji.ei102716cdg.dao.BanDao;
import es.uji.ei102716cdg.dao.ChatDao;
import es.uji.ei102716cdg.dao.OfferDao;
import es.uji.ei102716cdg.dao.RequestDao;
import es.uji.ei102716cdg.dao.SkillDao;
import es.uji.ei102716cdg.dao.StudentDao;
import es.uji.ei102716cdg.domain.chat.Chat;
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
	public List<Skill> getSkillsByPost(List<? extends Post> list) {
		List<Skill> listSkill = new ArrayList<Skill>();
		for (Post post : list){
			listSkill.add(skillDao.getSkill(post.getSkill_Id()));
		}
		
		return listSkill;
	}
	
	@Override
	public List<User> getUsersByPost(List<? extends Post> list) {
		List<User> listUser = new ArrayList<User>();
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

		Comparator<Post> comparatorPost = new Comparator<Post>() {
			@Override
			public int compare(Post p1, Post p2) {
				 return p2.getStartDate().compareTo(p1.getStartDate());				
			}
		};
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

	

}
