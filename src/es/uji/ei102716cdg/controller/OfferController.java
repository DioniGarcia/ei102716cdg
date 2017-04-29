package es.uji.ei102716cdg.controller;

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
import es.uji.ei102716cdg.service.PostServiceInterface;
import es.uji.ei102716cdg.validator.OfferValidator;


@Controller
@RequestMapping("db_test/offer")
public class OfferController {
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
		model.addAttribute("offers", offerDao.getOffers());
		return "db_test/offer/list";
	}
	
	@RequestMapping("/add")
	public String addOffer(Model model){
		model.addAttribute("offer",new Offer());
		model.addAttribute("nick_list", postService.getActiveStudentsNick());
		model.addAttribute("skill_list", postService.getActiveSkills());
		return "db_test/offer/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("offer") Offer offer,
													BindingResult bindingResult){
		
		OfferValidator offerValidator = new OfferValidator();
		offerValidator.validate(offer, bindingResult);
		if(bindingResult.hasErrors())
			return "db_test/offer/add";
		offerDao.addOffer(offer);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editOffer(Model model, @PathVariable int id){
		model.addAttribute("offer", offerDao.getOffer(id));
		model.addAttribute("nick_list", postService.getActiveStudentsNick());
		model.addAttribute("skill_list", postService.getActiveSkills());
		return "db_test/offer/update";
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String id,
								@ModelAttribute("offer") Offer offer,
								BindingResult bindingResult){
		OfferValidator offerValidator = new OfferValidator();
		offerValidator.validate(offer, bindingResult);
		if (bindingResult.hasErrors())
			return "db_test/offer/update";
		offerDao.updateOffer(offer);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable int id){
		offerDao.deleteOffer(id);
		return "redirect:../list.html";
	}
	
}