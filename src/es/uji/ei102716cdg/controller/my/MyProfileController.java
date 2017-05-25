package es.uji.ei102716cdg.controller.my;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei102716cdg.dao.StudentDao;
import es.uji.ei102716cdg.domain.user.Student;
import es.uji.ei102716cdg.domain.user.User;
import es.uji.ei102716cdg.service.PointsServiceInterface;


@Controller
@RequestMapping("my/profile")
public class MyProfileController {
	private StudentDao studentDao;
	private PointsServiceInterface pointsService;
	
	@Autowired
	public void setPointsService(PointsServiceInterface pointsService){
		this.pointsService = pointsService;
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
		model.addAttribute("totalHours", pointsService.getUserPoints(user.getNick()));
		model.addAttribute("receivedHours", pointsService.getReceivedHours(user.getNick()));
		model.addAttribute("offeredHours", pointsService.getOfferedHours(user.getNick()));
		return "my/profile/points";
	}
	
}
