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
import es.uji.ei102716cdg.validator.StudentValidator;


@Controller
@RequestMapping("my/profile")
public class MyProfileController {
	private StudentDao studentDao;
	
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
		return "my/profile/points";
	}
	
	@RequestMapping("/add")
	public String addStudent(Model model){
		model.addAttribute("student",new Student());
		return "db_test/student/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("student") Student student,
													BindingResult bindingResult){
		StudentValidator studentValidator = new StudentValidator();
		studentValidator.validate(student, bindingResult);
		if(bindingResult.hasErrors())
			return "db_test/student/add";
		studentDao.addStudent(student);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{nick}", method=RequestMethod.GET)
	public String editStudent(Model model, @PathVariable String nick){
		model.addAttribute("student", studentDao.getStudent(nick));
		return "db_test/student/update";
	}
	
	@RequestMapping(value="/update/{nick}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String nick,
								@ModelAttribute("student") Student student,
								BindingResult bindingResult){
		StudentValidator studentValidator = new StudentValidator();
		studentValidator.validate(student, bindingResult);
		if (bindingResult.hasErrors())
			return "db_test/student/update";
		studentDao.updateStudent(student);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{nick}")
	public String processDelete(@PathVariable String nick){
		studentDao.deleteStudent(nick);
		return "redirect:../list.html";
	}
	
}
