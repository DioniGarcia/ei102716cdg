package es.uji.ei102716cdg.controller.my;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.StudentDao;
import es.uji.ei102716cdg.domain.user.Student;
import es.uji.ei102716cdg.domain.user.User;
import es.uji.ei102716cdg.service.PostService;
import es.uji.ei102716cdg.service.PostServiceInterface;
import es.uji.ei102716cdg.validator.StudentValidator;


@Controller
@RequestMapping("my/profile")
public class MyProfileController {
	private StudentDao studentDao;
	private PostServiceInterface postService;
	
	@Autowired
	public void setPostService(PostServiceInterface postService){
		this.postService = postService;
	}
	
	@Autowired
	public void setStudentDao(StudentDao studentDao){
		this.studentDao=studentDao;
	}
	
	@RequestMapping("/index")
	public String getUser(Model model, HttpSession session){
		User user = (User) session.getAttribute("user");
		Student student = studentDao.getStudent(user.getNick());
		model.addAttribute("student", student);
		return "my/profile/index";
	}
	
	@RequestMapping("/points")
	public String getPointsPage(Model model, HttpSession session){
		User user = (User) session.getAttribute("user");
		Student student = studentDao.getStudent(user.getNick());
		model.addAttribute("student", student);
		model.addAttribute("totalHours", postService.getUserPoints(user.getNick()));
		model.addAttribute("receivedHours", postService.getReceivedHours(user.getNick()));
		model.addAttribute("offeredHours", postService.getOfferedHours(user.getNick()));
		return "my/profile/points";
	}
	
}
