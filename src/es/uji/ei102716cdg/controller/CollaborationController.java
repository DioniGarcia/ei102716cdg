package es.uji.ei102716cdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.CollaborationDao;
import es.uji.ei102716cdg.dao.OfferDao;
import es.uji.ei102716cdg.dao.RequestDao;
import es.uji.ei102716cdg.domain.Collaboration;


@Controller
@RequestMapping("/collaboration")
public class CollaborationController {
	private CollaborationDao collaborationDao;
	private OfferDao offerDao;
	private RequestDao requestDao;
	
	@Autowired
	public void setCollaborationDao(CollaborationDao collaborationDao){
		this.collaborationDao=collaborationDao;
	}
	
	@Autowired
	public void setOfferDao(OfferDao offerDao){
		this.offerDao=offerDao;
	}
	
	@Autowired
	public void setRequestDao(RequestDao requestDao){
		this.requestDao=requestDao;
	}
	
	@RequestMapping("/list")
	public String listCollaborations(Model model){
		model.addAttribute("collaborations", collaborationDao.getCollaborations());
		return "collaboration/list";
	}
	
	@RequestMapping("/add")
	public String addCollaboration(Model model){
		model.addAttribute("collaboration",new Collaboration());
		model.addAttribute("offers",offerDao.getOffers());
		model.addAttribute("requests",requestDao.getRequests());
		return "collaboration/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("collaboration") Collaboration collaboration,
													BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "collaboration/add";
		collaborationDao.addCollaboration(collaboration);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editCollaboration(Model model, @PathVariable int id){
		model.addAttribute("collaboration", collaborationDao.getCollaboration(id));
		model.addAttribute("offers",offerDao.getOffers());
		model.addAttribute("requests",requestDao.getRequests());
		return "collaboration/update";
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String id,
								@ModelAttribute("collaboration") Collaboration collaboration,
								BindingResult bindingResult){
		if (bindingResult.hasErrors())
			return "collaboration/update";
		collaborationDao.updateCollaboration(collaboration);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable int id){
		collaborationDao.deleteCollaboration(id);
		return "redirect:../list.html";
	}
	
}

