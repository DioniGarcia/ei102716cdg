package es.uji.ei102716cdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.SkillDao;
import es.uji.ei102716cdg.domain.Skill;
import es.uji.ei102716cdg.domain.SkillWrapper;


@Controller
@RequestMapping("/skill")
public class SkillController {
	private SkillDao skillDao;
	
	@Autowired
	public void setSkillDao(SkillDao skillDao){
		this.skillDao=skillDao;
	}
	
	@RequestMapping("/list")
	public String listSkills(Model model){
		model.addAttribute("skills", skillDao.getSkills());
		return "skill/list";
	}
	
	@RequestMapping("/add")
	public String addSkills(Model model){
		model.addAttribute("skillWrapper",new SkillWrapper());
		return "skill/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("skillWrapper") SkillWrapper skillWrapper,
													BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "skill/add";
		for(Skill skill : skillWrapper.getSkills()){
			skillDao.addSkill(skill);
		}
		
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editSkill(Model model, @PathVariable int id){
		model.addAttribute("skill", skillDao.getSkill(id));
		return "skill/update";
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String id,
								@ModelAttribute("skill") Skill skill,
								BindingResult bindingResult){
		if (bindingResult.hasErrors())
			return "skill/update";
		skillDao.updateSkill(skill);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable int id){
		skillDao.deleteSkill(id);
		return "redirect:../list.html";
	}
	
}

