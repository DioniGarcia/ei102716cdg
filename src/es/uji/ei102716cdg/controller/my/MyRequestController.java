package es.uji.ei102716cdg.controller.my;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.RequestDao;
import es.uji.ei102716cdg.domain.collaboration.Request;
import es.uji.ei102716cdg.domain.user.User;
import es.uji.ei102716cdg.service.PostServiceInterface;
import es.uji.ei102716cdg.util.CustomSqlDateEditor;
import es.uji.ei102716cdg.validator.RequestValidator;


@Controller
@RequestMapping("my/request")
public class MyRequestController {
	@Autowired
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
	
	@InitBinder("request")
    public void customizeBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, 
                                    new CustomSqlDateEditor(dateFormatter, true,10));
    }
	
	@RequestMapping("/add")
	public String addRequest(Model model){
		model.addAttribute("request",new Request());
		model.addAttribute("skill_list", postService.getActiveSkills());
		return "my/request/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addRequest(HttpSession session, @ModelAttribute("request") Request request,
			BindingResult bindingResult){
		
		RequestValidator requestValidator = new RequestValidator();
		requestValidator.validate(request, bindingResult);
		if(bindingResult.hasErrors())
			return "my/request/add";
		User user = (User) session.getAttribute("user");
		request.setStudent_nick(user.getNick());
		requestDao.addRequest(request);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listRequests(Model model, HttpSession session){
		User user = (User) session.getAttribute("user");
		List<Request> requestsByNick = requestDao.getRequestsByNick(user.getNick());
		model.addAttribute("requests", requestsByNick);
		model.addAttribute("skills", postService.getSkillsByPost(requestsByNick));
		return "my/request/list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable int id){
		requestDao.deleteRequest(id);
		return "redirect:list.html";
	}
	
}
