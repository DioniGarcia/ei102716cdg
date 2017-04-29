package es.uji.ei102716cdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.AdministratorDao;
import es.uji.ei102716cdg.domain.user.Administrator;
import es.uji.ei102716cdg.validator.AdministratorValidator;


@Controller
@RequestMapping("db_test/administrator")
public class AdministratorController {
	private AdministratorDao administratorDao;
	
	@Autowired
	public void setAdministratorDao(AdministratorDao administratorDao){
		this.administratorDao=administratorDao;
	}
	
	@RequestMapping("/list")
	public String listAdministrator(Model model){
		model.addAttribute("administrators", administratorDao.getAdministrators());
		return "db_test/administrator/list";
	}
	
	@RequestMapping("/add")
	public String addAdministrator(Model model){
		model.addAttribute("administrator",new Administrator());
		return "db_test/administrator/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("administrator") Administrator administrator,
													BindingResult bindingResult){
		AdministratorValidator administratorValidator = new AdministratorValidator();
		administratorValidator.validate(administrator, bindingResult);
		if(bindingResult.hasErrors())
			return "db_test/administrator/add";
		administratorDao.addAdministrator(administrator);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{nick}", method=RequestMethod.GET)
	public String editAdministrator(Model model, @PathVariable String nick){
		model.addAttribute("administrator", administratorDao.getAdministrator(nick));
		return "db_test/administrator/update";
	}
	
	@RequestMapping(value="/update/{nick}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String nick,
								@ModelAttribute("administrator") Administrator administrator,
								BindingResult bindingResult){
		AdministratorValidator administratorValidator = new AdministratorValidator();
		administratorValidator.validate(administrator, bindingResult);
		if (bindingResult.hasErrors())
			return "db_test/administrator/update";
		administratorDao.updateAdministrator(administrator);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{nick}")
	public String processDelete(@PathVariable String nick){
		administratorDao.deleteAdministrator(nick);
		return "redirect:../list.html";
	}
	
}
