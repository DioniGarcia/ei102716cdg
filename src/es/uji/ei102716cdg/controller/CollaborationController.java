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
import es.uji.ei102716cdg.domain.collaboration.Collaboration;
import es.uji.ei102716cdg.service.PostServiceInterface;


@Controller
@RequestMapping("db_test/collaboration")
public class CollaborationController {
	private CollaborationDao collaborationDao;
	private PostServiceInterface postService;

	@Autowired
	public void setPostService(PostServiceInterface postService){
		this.postService = postService;
	}
	
	@Autowired
	public void setCollaborationDao(CollaborationDao collaborationDao){
		this.collaborationDao=collaborationDao;
	}
	
	@RequestMapping("/list")
	public String listCollaborations(Model model){
		model.addAttribute("collaborations", collaborationDao.getCollaborations());
		return "db_test/collaboration/list";
	}
	
	@RequestMapping("/add")
	public String addCollaboration(Model model){
		model.addAttribute("collaboration",new Collaboration());
		model.addAttribute("offers", postService.getActiveOffers());
		model.addAttribute("requests",postService.getActiveRequests());
		return "db_test/collaboration/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("collaboration") Collaboration collaboration,
													BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "db_test/collaboration/add";
		collaborationDao.addCollaboration(collaboration);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{collaboration_id}", method=RequestMethod.GET)
	public String editCollaboration(Model model, @PathVariable int collaboration_id){
		model.addAttribute("collaboration", collaborationDao.getCollaboration(collaboration_id));
		model.addAttribute("offers", postService.getActiveOffers());
		model.addAttribute("requests",postService.getActiveRequests());
		return "db_test/collaboration/update";
	}
	
	@RequestMapping(value="/update/{collaboration_id}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable int collaboration_id,
								@ModelAttribute("collaboration") Collaboration collaboration,
								BindingResult bindingResult){
		if (bindingResult.hasErrors())
			return "db_test/collaboration/update";
		collaborationDao.updateCollaboration(collaboration);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{collaboration_id}")
	public String processDelete(@PathVariable int collaboration_id){
		collaborationDao.deleteCollaboration(collaboration_id);
		return "redirect:../list.html";
	}
	
}