package es.uji.ei102716cdg.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei102716cdg.dao.OfferDao;
import es.uji.ei102716cdg.domain.collaboration.Offer;
import es.uji.ei102716cdg.domain.user.User;
import es.uji.ei102716cdg.service.PostServiceInterface;

@Controller
public class HomeController {

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
	
	@RequestMapping("/index")
	public String index(Model model, HttpSession session, @ModelAttribute("user") User user) {
		if (session.getAttribute("admin") != null)
			return "redirect:admin/index.html";
		if (session.getAttribute("user") == null)
			return "login/login";
		model.addAttribute("user", new User());
		List<Offer> recentOffers = postService.getRecentOffers();
		model.addAttribute("offers", recentOffers);
		model.addAttribute("skills", postService.getSkillsByOffers(recentOffers));
		model.addAttribute("users", postService.getUsersByOffers(recentOffers));
		return "index";
	}
}
