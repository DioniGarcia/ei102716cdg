package es.uji.ei102716cdg.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei102716cdg.domain.collaboration.Offer;
import es.uji.ei102716cdg.domain.collaboration.Request;
import es.uji.ei102716cdg.domain.user.Student;
import es.uji.ei102716cdg.domain.user.User;
import es.uji.ei102716cdg.service.PostServiceInterface;

@Controller
public class HomeController {

	@Autowired
	private PostServiceInterface postService;
	
	@Autowired
	public void setPostService(PostServiceInterface postService){
		this.postService = postService;
	}
	
	@RequestMapping("/index")
	public String index(Model model, HttpSession session, @ModelAttribute("user") User user) {
		if (session.getAttribute("admin") != null)
			return "redirect:admin/index.html";
		if (session.getAttribute("user") == null)
			return "login/login";
		
		user = (User) session.getAttribute("user");
		session.setAttribute("user", user);
		
		//Se añaden los datos necesarios para la oferta
		List<Offer> recentOffers = postService.getActiveRecentOffers(2, user.getNick());
		List<Student> studentsOf = postService.getStudentsByPost(recentOffers);
		model.addAttribute("offers", recentOffers);
		model.addAttribute("skillsOf", postService.getSkillsByPost(recentOffers));
		model.addAttribute("studentsOf", studentsOf);
		model.addAttribute("ratingsOf", postService.getRatingByStudents(studentsOf));
		
		//Se añaden los datos necesarios para la demanda
		List<Request> recentRequest = postService.getActiveRecentRequests(2, user.getNick());
		List<Student> studentsRq = postService.getStudentsByPost(recentRequest);
		model.addAttribute("requests", recentRequest);
		model.addAttribute("skillsRq", postService.getSkillsByPost(recentRequest));
		model.addAttribute("studentsRq", studentsRq);
		model.addAttribute("ratingsRq", postService.getRatingByStudents(studentsRq));
		return "index";
	}
	
}
