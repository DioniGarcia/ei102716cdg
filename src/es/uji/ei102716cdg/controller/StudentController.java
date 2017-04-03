package es.uji.ei102716cdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.StudentDao;
import es.uji.ei102716cdg.domain.Student;


@Controller
@RequestMapping("/student")
public class StudentController {
	private StudentDao studentDao;
	
	@Autowired
	public void setStudentDao(StudentDao studentDao){
		this.studentDao=studentDao;
	}
	
	@RequestMapping("/list")
	public String listStudents(Model model){
		model.addAttribute("students", studentDao.getStudents());
		return "student/list";
	}
	
	@RequestMapping("/add")
	public String addStudent(Model model){
		model.addAttribute("student",new Student());
		return "student/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("student") Student student,
													BindingResult bindingResult){
		StudentValidator studentValidator = new StudentValidator();
		studentValidator.validate(student, bindingResult);
		if(bindingResult.hasErrors())
			return "student/add";
		studentDao.addStudent(student);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{userName}", method=RequestMethod.GET)
	public String editStudent(Model model, @PathVariable String userName){
		model.addAttribute("student", studentDao.getStudent(userName));
		return "student/update";
	}
	
	@RequestMapping(value="/update/{userName}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String userName,
								@ModelAttribute("student") Student student,
								BindingResult bindingResult){
		StudentValidator studentValidator = new StudentValidator();
		studentValidator.validate(student, bindingResult);
		if (bindingResult.hasErrors())
			return "student/update";
		studentDao.updateStudent(student);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{userName}")
	public String processDelete(@PathVariable String userName){
		studentDao.deleteStudent(userName);
		return "redirect:../list.html";
	}
	
}
