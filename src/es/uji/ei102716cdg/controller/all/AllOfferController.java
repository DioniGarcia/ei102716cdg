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
											@RequestParam(value="pageSize", required=false) Integer pageSize){
		
		if (pageSize == null) pageSize = 5;
		if (page == null) page = 1;
		
		user = (User) session.getAttribute("user");
		session.setAttribute("user", user);
		
		int pageCount = postService.getOffersPageCount(pageSize, user.getNick());
		
		List<Offer> recentOffers = postService.getPaginatedOffers(pageSize, page, user.getNick());
		model.addAttribute("offers", recentOffers);
		model.addAttribute("skills", postService.getSkillsByPost(recentOffers));
		model.addAttribute("students", postService.getStudentsByPost(recentOffers));
		model.addAttribute("pageCount", pageCount);
		return "all/offer/list";
	}
	
	@RequestMapping("/{id}")
	public String showOffer(Model model,  @PathVariable int id){
		Offer offer = offerDao.getOffer(id);
		model.addAttribute("offer", offer);
		model.addAttribute("skill", postService.getSkillById(offer.getSkill_Id()));
		model.addAttribute("student", postService.getStudentByNick(offer.getStudent_nick()));
		return "all/offer/info";
	}

}
