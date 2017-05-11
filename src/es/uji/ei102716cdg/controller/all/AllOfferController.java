package es.uji.ei102716cdg.controller.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei102716cdg.dao.OfferDao;
import es.uji.ei102716cdg.domain.collaboration.Offer;
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
	public String listOffers(Model model){
		List<Offer> recentOffers = postService.getRecentOffers();
		model.addAttribute("offers", recentOffers);
		model.addAttribute("skills", postService.getSkillsByPost(recentOffers));
		model.addAttribute("users", postService.getUsersByPost(recentOffers));
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
