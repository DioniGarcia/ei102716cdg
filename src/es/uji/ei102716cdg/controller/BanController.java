package es.uji.ei102716cdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.BanDao;
import es.uji.ei102716cdg.domain.user.Ban;
import es.uji.ei102716cdg.validator.BanValidator;


@Controller
@RequestMapping("db_test/ban")
public class BanController {
	private BanDao banDao;
	
	@Autowired
	public void setBanDao(BanDao banDao){
		this.banDao=banDao;
	}
	
	@RequestMapping("/list")
	public String listBans(Model model){
		model.addAttribute("bans", banDao.getBans());
		return "db_test/ban/list";
	}
	
	@RequestMapping("/add")
	public String addBan(Model model){
		model.addAttribute("ban",new Ban());
		return "db_test/ban/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("ban") Ban ban,
													BindingResult bindingResult){
		BanValidator banValidator = new BanValidator();
		banValidator.validate(ban, bindingResult);
		if(bindingResult.hasErrors())
			return "db_test/ban/add";
		banDao.addBan(ban);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{studentNick}", method=RequestMethod.GET)
	public String editStudent(Model model, @PathVariable String studentNick){
		model.addAttribute("ban", banDao.getBan(studentNick));
		return "db_test/ban/update";
	}
	
	@RequestMapping(value="/update/{studentNick}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String studentNick,
								@ModelAttribute("ban") Ban ban,
								BindingResult bindingResult){
		BanValidator banValidator = new BanValidator();
		banValidator.validate(ban, bindingResult);
		if (bindingResult.hasErrors())
			return "db_test/ban/update";
		banDao.updateBan(ban);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{studentNick}")
	public String processDelete(@PathVariable String studentNick){
		banDao.deleteBan(studentNick);
		return "redirect:../list.html";
	}
	
}