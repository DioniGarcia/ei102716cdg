package es.uji.ei102716cdg.controller.all;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.uji.ei102716cdg.dao.OfferDao;
import es.uji.ei102716cdg.domain.collaboration.Offer;
import es.uji.ei102716cdg.domain.collaboration.Request;
import es.uji.ei102716cdg.domain.user.Student;
import es.uji.ei102716cdg.domain.user.User;
import es.uji.ei102716cdg.service.PostServiceInterface;

@Controller
@RequestMapping("all/offers")
public class AllOfferController {
	
	@Autowired
	private OfferDao offerDao;
	private PostServiceInterface postService;
	
	@Autowired
	public void setPostService(PostServiceInterface postService){
		this.postService = postService;
	}
	
	@Autowired
	public void setOfferDao(OfferDao offerDao){
		this.offerDao=offerDao;
	}
	
	@RequestMapping("/list")
	public String listOffers(Model model, HttpSession session, @ModelAttribute("user") User user,
											@RequestParam(value="page", required=false) Integer page,
											@RequestParam(value="pageSize", required=false) Integer pageSize,
											@RequestParam(value="q", required=false) String q){
		
		if (pageSize == null) pageSize = 5;
		if (page == null) page = 1;
		
		user = (User) session.getAttribute("user");
		session.setAttribute("user", user);
		
		List<Offer> paginatedOffers = null;
		List<Offer> recentOffers = null;
		
		if (q == null){
			recentOffers = postService.getActiveRecentOffers(0, user.getNick());
		} else { 
			recentOffers = postService.searchOffers(q, user.getNick());
			model.addAttribute("q", q);
		}
		
		paginatedOffers = postService.getPaginatedOffers(recentOffers, pageSize, page, user.getNick());
		int pageCount = postService.getOffersPageCount(recentOffers, pageSize, user.getNick());
		
		List<Student> students = postService.getStudentsByPost(paginatedOffers);
		model.addAttribute("offers", paginatedOffers);
		model.addAttribute("skills", postService.getSkillsByPost(paginatedOffers));
		model.addAttribute("students", students);
		model.addAttribute("ratings", postService.getRatingByStudents(students));
		model.addAttribute("pageCount", pageCount);
		return "all/offer/list";
	}
	
	@RequestMapping("/{id}")
	public String showOffer(Model model,  @PathVariable int id, HttpSession session){
		User user = (User) session.getAttribute("user");
		Offer offer = offerDao.getOffer(id);
		Student student = postService.getStudentByNick(offer.getStudent_nick());
		model.addAttribute("offer", offer);
		model.addAttribute("skill", postService.getSkillById(offer.getSkill_Id()));
		model.addAttribute("student", student);
		model.addAttribute("rating", postService.getRating(student.getNick()));
		
		List<Request> requests = postService.getRequestsBySkillId(user.getNick(), offer.getSkill_Id());
		model.addAttribute("skills", postService.getSkillsByPost(requests));
		model.addAttribute("requests", requests);
		return "all/offer/info";
	}

}
