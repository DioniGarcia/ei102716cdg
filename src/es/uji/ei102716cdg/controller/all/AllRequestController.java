package es.uji.ei102716cdg.controller.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei102716cdg.dao.RequestDao;
import es.uji.ei102716cdg.domain.collaboration.Request;
import es.uji.ei102716cdg.service.PostServiceInterface;

@Controller
@RequestMapping("all/requests")
public class AllRequestController {
	
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
	
	@RequestMapping("/list")
	public String listRequests(Model model){
		List<Request> recentRequests = postService.getRecentRequests();
		model.addAttribute("requests", recentRequests);
		model.addAttribute("skills", postService.getSkillsByPost(recentRequests));
		model.addAttribute("users", postService.getUsersByPost(recentRequests));
		return "all/request/list";
	}
	
	@RequestMapping("/{id}")
	public String showRequest(Model model,  @PathVariable int id){
		Request request = requestDao.getRequest(id);
		model.addAttribute("request", request);
		model.addAttribute("skill", postService.getSkillById(request.getSkill_Id()));
		model.addAttribute("student", postService.getStudentByNick(request.getStudent_nick()));
		return "all/request/info";
	}

}
