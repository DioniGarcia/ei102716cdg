package es.uji.ei102716cdg.controller.all;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.uji.ei102716cdg.dao.RequestDao;
import es.uji.ei102716cdg.domain.collaboration.Offer;
import es.uji.ei102716cdg.domain.collaboration.Request;
import es.uji.ei102716cdg.domain.user.Student;
import es.uji.ei102716cdg.domain.user.User;
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
	public String listRequests(Model model, HttpSession session, @ModelAttribute("user") User user,
					@RequestParam(value="page", required=false) Integer page,
					@RequestParam(value="pageSize", required=false) Integer pageSize,
					@RequestParam(value="q", required=false) String q){
		
		if (pageSize == null) pageSize = 5;
		if (page == null) page = 1;
		
		user = (User) session.getAttribute("user");
		session.setAttribute("user", user);
		
		List<Request> recentRequests = null;
		List<Request> paginatedRequests = null;
		
		if (q == null){
			recentRequests = postService.getActiveRecentRequests(0, user.getNick());
		} else { 
			recentRequests = postService.searchRequests(q, user.getNick());
			model.addAttribute("q", q);
		}
		
		paginatedRequests = postService.getPaginatedRequests(recentRequests, pageSize, page, user.getNick());
		int pageCount = postService.getRequestsPageCount(recentRequests, pageSize, user.getNick());
		
		List<Student> students = postService.getStudentsByPost(paginatedRequests);
		model.addAttribute("requests", paginatedRequests);
		model.addAttribute("skills", postService.getSkillsByPost(paginatedRequests));
		model.addAttribute("students", students);
		model.addAttribute("ratings", postService.getRatingByStudents(students));
		model.addAttribute("pageCount", pageCount);
		return "all/request/list";
	}
	
	@RequestMapping("/{id}")
	public String showRequest(Model model,  @PathVariable int id, HttpSession session){
		User user = (User) session.getAttribute("user");
		Request request = requestDao.getRequest(id);
		Student student = postService.getStudentByNick(request.getStudent_nick());
		model.addAttribute("request", request);
		model.addAttribute("skill", postService.getSkillById(request.getSkill_Id()));
		model.addAttribute("student", student);
		model.addAttribute("rating", postService.getRating(student.getNick()));
		
		List<Offer> offers = postService.getOffersBySkillId(user.getNick(), request.getSkill_Id());
		model.addAttribute("skills", postService.getSkillsByPost(offers));
		model.addAttribute("offers", offers);
		return "all/request/info";
	}

}
