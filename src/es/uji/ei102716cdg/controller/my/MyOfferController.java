package es.uji.ei102716cdg.controller.my;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.OfferDao;
import es.uji.ei102716cdg.domain.collaboration.Offer;
import es.uji.ei102716cdg.domain.user.User;
import es.uji.ei102716cdg.service.PostServiceInterface;
import es.uji.ei102716cdg.validator.OfferValidator;


@Controller
@RequestMapping("my/offers")
public class MyOfferController {
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
	
	@RequestMapping("/add")
	public String addOffer(Model model){
		model.addAttribute("offer",new Offer());
		model.addAttribute("skill_list", postService.getActiveSkills());
		return "my/offer/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addOffer(HttpSession session, @ModelAttribute("offer") Offer offer,
			BindingResult bindingResult){
		
		OfferValidator offerValidator = new OfferValidator();
		offerValidator.validate(offer, bindingResult);
		if(bindingResult.hasErrors())
			return "my/offer/add";
		User user = (User) session.getAttribute("user");
		offer.setStudent_nick(user.getNick());
		offerDao.addOffer(offer);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listOffers(Model model, HttpSession session){
		User user = (User) session.getAttribute("user");
		List<Offer> offersByNick = offerDao.getOffersByNick(user.getNick());
		model.addAttribute("offers", offersByNick);
		model.addAttribute("skills", postService.getSkillsByOffers(offersByNick));
		return "my/offer/list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable int id){
		offerDao.deleteOffer(id);
		return "redirect:list.html";
	}
	
}
