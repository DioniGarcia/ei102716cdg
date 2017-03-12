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
import es.uji.ei102716cdg.domain.Offer;


@Controller
@RequestMapping("/offer")
public class OfferController {
	private OfferDao offerDao;
	
	@Autowired
	public void setOfferDao(OfferDao offerDao){
		this.offerDao=offerDao;
	}
	
	@RequestMapping("/list")
	public String listOffers(Model model){
		model.addAttribute("offers", offerDao.getOffers());
		return "offer/list";
	}
	
	@RequestMapping("/add")
	public String addOffer(Model model){
		model.addAttribute("offer",new Offer());
		model.addAttribute("nifs", offerDao.getNifsId());
		model.addAttribute("skills", offerDao.getSkillsId());
		return "offer/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("offer") Offer offer,
													BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "offer/add";
		offerDao.addOffer(offer);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editOffer(Model model, @PathVariable int id){
		model.addAttribute("offer", offerDao.getOffer(id));
		return "offer/update";
	}
	
	@RequestMapping(value="/update/{userName}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String userName,
								@ModelAttribute("offer") Offer offer,
								BindingResult bindingResult){
		if (bindingResult.hasErrors())
			return "offer/update";
		offerDao.updateOffer(offer);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable int id){
		offerDao.deleteOffer(id);
		return "redirect:../list.html";
	}
	
}

