package es.uji.ei102716cdg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.StudentDao;
import es.uji.ei102716cdg.dao.UserDao;
import es.uji.ei102716cdg.domain.user.Student;
import es.uji.ei102716cdg.domain.user.User;
import es.uji.ei102716cdg.validator.StudentValidator;

@Controller
public class SignupController {


	@Autowired
	private UserDao userDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
	}
	
	@Autowired
	public void setStudentDao(StudentDao studentDao){
		this.studentDao=studentDao;
	}
	
	@RequestMapping("/signup")
	public String login(Model model, HttpSession session) {
		if (session.getAttribute("user") != null)
			return "redirect:index.jsp";
		model.addAttribute("student", new Student());
		return "login/register";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("student") Student student, HttpSession session,
													BindingResult bindingResult){
		StudentValidator studentValidator = new StudentValidator();
		studentValidator.validate(student, bindingResult);
		if (userDao.existsUsername(student.getNick()))
			bindingResult.rejectValue("nick", "existe",
					"El nombre de usuario ya existe");
		if(bindingResult.hasErrors())
			return "login/register";
		student.setPasswd(userDao.encodePassword(student.getPasswd()));
		studentDao.addStudent(student);
		session.setAttribute("user", (User) student);
		return "redirect:index.html";
	}
}
