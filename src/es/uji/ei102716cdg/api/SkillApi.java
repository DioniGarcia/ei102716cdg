package es.uji.ei102716cdg.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.uji.ei102716cdg.dao.SkillDao;
import es.uji.ei102716cdg.domain.skill.Skill;
import es.uji.ei102716cdg.util.Encoding;

@Controller
@RequestMapping("api/skill")
public class SkillApi {
	
private SkillDao skillDao;
	
	@Autowired
	public void setSkillDao(SkillDao skillDao){
		this.skillDao=skillDao;
	}
	

	@RequestMapping( method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getById(@RequestParam("id") int id){
		Skill skill = skillDao.getSkill(id);
		return "{\"id\": " + id + ", "
				+ "\"name\": \"" + Encoding.convertFromUTF8(skill.getName()) + "\", "
				+ "\"level\": \"" + skill.getLevel() + "\", "
				+ "\"description\": \"" + Encoding.convertFromUTF8(skill.getDescription()) + "\"}";
	}
	
	@RequestMapping(value = "/{id}/levels", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getSkillLevels(@PathVariable int id){
		return "{text: \"getSkillLevels\"}";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String searchSkill(@RequestParam("name") String name){
		String result = "";
		List<String> skills = skillDao.searchSkill(name);
		result += "[";
		int id = 0;	
		for(String skill : skills){
			result += "{\"id\": " + id++ + ", "
					+ "\"text\": \"" + Encoding.convertFromUTF8(skill) + "\"},";
		}
		return result.substring(0, result.length()-1) + "]";
	}
	
	@RequestMapping(value="/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAllSkills(){
		String result = "";
		List<Skill> skills = skillDao.getSkills();
		result += "[";
		for(Skill skill : skills){
			result += "{\"id\": " + skill.getSkill_id() + ", "
					+ "\"text\": \"" + Encoding.convertFromUTF8(skill.getName()) + "\"}, ";
		}
		return result.substring(0, result.length()-2) + "]";
	}
}
