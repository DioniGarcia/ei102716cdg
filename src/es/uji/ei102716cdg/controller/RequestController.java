package es.uji.ei102716cdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.RequestDao;
import es.uji.ei102716cdg.domain.collaboration.Request;
import es.uji.ei102716cdg.service.PostServiceInterface;
import es.uji.ei102716cdg.validator.RequestValidator;


@Controller
@RequestMapping("db_test/request")
public class RequestController {
	private RequestDao requestDao;
	private PostServiceInterface postService;

	@Autowired
	public void setPostService(PostServiceInterface postService){
		this.postService = postService;
	}
	
	@Autowired
	public void setRequestDao(RequestDao requestDao){
		this.requestDao=requestDao;
	}
	
	@RequestMapping("/list")
	public String listRequests(Model model){
		model.addAttribute("requests", requestDao.getRequests());
		return "db_test/request/list";
	}
	
	@RequestMapping("/add")
	public String addRequest(Model model){
		model.addAttribute("request",new Request());
		model.addAttribute("nick_list", postService.getActiveStudentsNick());
		model.addAttribute("skill_list", postService.getActiveSkills());
		return "db_test/request/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("request") Request request,
													BindingResult bindingResult){
		
		RequestValidator requestValidator = new RequestValidator();
		requestValidator.validate(request, bindingResult);
		if(bindingResult.hasErrors())
			return "db_test/request/add";
		requestDao.addRequest(request);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editRequest(Model model, @PathVariable int id){
		model.addAttribute("request", requestDao.getRequest(id));
		model.addAttribute("nick_list", postService.getActiveStudentsNick());
		model.addAttribute("skill_list", postService.getActiveSkills());
		return "db_test/request/update";
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String id,
								@ModelAttribute("request") Request request,
								BindingResult bindingResult){
		RequestValidator requestValidator = new RequestValidator();
		requestValidator.validate(request, bindingResult);
		if (bindingResult.hasErrors())
			return "db_test/request/update";
		requestDao.updateRequest(request);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable int id){
		requestDao.deleteRequest(id);
		return "redirect:../list.html";
	}
	
}