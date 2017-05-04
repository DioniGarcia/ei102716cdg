package es.uji.ei102716cdg.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.SkillDao;
import es.uji.ei102716cdg.domain.skill.Skill;
import es.uji.ei102716cdg.domain.skill.SkillWrapper;
import es.uji.ei102716cdg.util.Encoding;
import es.uji.ei102716cdg.validator.SkillValidator;

@Controller
@RequestMapping("db_test/skill")
public class SkillController {
	private SkillDao skillDao;
	
	@Autowired
	public void setSkillDao(SkillDao skillDao){
		this.skillDao=skillDao;
	}
	
	@RequestMapping("/list")
	public String listSkills(Model model){
		model.addAttribute("skills", skillDao.getSkills());
		return "db_test/skill/list";
	}
	
	@RequestMapping("/add")
	public String addSkills(Model model){
		model.addAttribute("skillWrapper",new SkillWrapper());
		return "db_test/skill/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("skillWrapper") SkillWrapper skillWrapper,
													BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "db_test/skill/add";
		
		for(int i=0; i<skillWrapper.getDescriptionList().length; i++){
			
		}
		for(Skill skill : skillWrapper.getSkills()){
			skillDao.addSkill(skill);
		}
		
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{skill_id}", method=RequestMethod.GET)
	public String editSkill(Model model, @PathVariable int skill_id){
		model.addAttribute("skill", skillDao.getSkill(skill_id));
		return "db_test/skill/update";
	}
	
	@RequestMapping(value="/update/{skill_id}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable int skill_id,
								@ModelAttribute("skill") Skill skill,
								BindingResult bindingResult){
		SkillValidator skillValidator = new SkillValidator();
		skillValidator.validate(skill, bindingResult);
		if (bindingResult.hasErrors())
			return "db_test/skill/update";
		skillDao.updateSkill(skill);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{name}")
	public String processDelete(@PathVariable String name){
		for (Skill skill : skillDao.getSkills()){
			if(skill.getName().equals(Encoding.convertLatinToUTF8(name))){
				skillDao.deleteSkill(skill.getSkill_id());
			}
		}
		return "redirect:../list.html";
	}
	
}
