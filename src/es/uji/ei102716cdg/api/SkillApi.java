package es.uji.ei102716cdg.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	/**Para una sola skill, genera una cadena en formato Json
	 * 
	 * @param id 	Identificador único de la Skill en un nivel concreto
	 * @return		Cadena con la skill en formato Json 
	 */
	@RequestMapping( method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getById(@RequestParam("id") int id){
		Skill skill = skillDao.getSkill(id);
		return "{\"id\": \"" + id + "\", "
				+ "\"name\": \"" + Encoding.convertUTF8ToLatin(skill.getName()) + "\", "
				+ "\"level\": \"" + skill.getLevel() + "\", "
				+ "\"description\": \"" + Encoding.convertUTF8ToLatin(skill.getDescription()) + "\"}";
	}
	
	/**Para todos los niveles de una skill, genera una cadena en formato Json
	 * 
	 * @param name	Nombre de la skill
	 * @return		Cadena con los distintos niveles de la skill en formato Json
	 */
	@RequestMapping(value = "/levels", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getSkillLevels(@RequestParam("name") String name){
		String result = "";
		List<Skill> skills = skillDao.skillLevels(name);
		if (skills.isEmpty()) return "[]";
		result += "[";
		for(Skill skill : skills){
			result += "{\"id\": \"" + skill.getSkill_id() + "\", "
					+ "\"text\": \"" + 	Encoding.convertUTF8ToLatin(skill.getLevel()) + ": " +
										Encoding.convertUTF8ToLatin(skill.getDescription()) + "\"},";
		}
		return result.substring(0, result.length()-1) + "]";
	}
	
	
	@RequestMapping(value="/search", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String searchSkill(@RequestParam("name") String name){
		String result = "";
		List<String> skills = skillDao.searchSkill(Encoding.convertLatinToUTF8(name));
		if (skills.isEmpty()) return "[]";
		result += "[";
		for(String skill : skills){
			result += "{\"id\": \"" + Encoding.convertUTF8ToLatin(skill) + "\", "
					+ "\"text\": \"" + Encoding.convertUTF8ToLatin(skill) + "\"},";
		}
		return result.substring(0, result.length()-1) + "]";
	}
	
	@RequestMapping(value="/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAllSkills(){
		String result = "";
		List<Skill> skills = skillDao.getSkills();
		result += "[";
		for(Skill skill : skills){
			int numberOffers = skillDao.getNumberOfOffers(skill.getSkill_id());
			int numberRequests = skillDao.getNumberOfRequests(skill.getSkill_id());
			result += "{\"id\": \"" + skill.getSkill_id() + "\", "
					+ "\"name\": \"" + Encoding.convertUTF8ToLatin(skill.getName()) + "\", "
					+ "\"description\": \"" + Encoding.convertUTF8ToLatin(skill.getDescription()) + "\", "
					+ "\"offers\": \"" + Encoding.convertUTF8ToLatin(Integer.toString(numberOffers)) + "\", "
					+ "\"requests\": \"" + Encoding.convertUTF8ToLatin(Integer.toString(numberRequests)) + "\", "
					+ "\"level\": \"" + Encoding.convertUTF8ToLatin(skill.getLevel()) + "\", "
					+ "\"active\": " + (skill.isActive() ? "true" : "false") + "}, ";
		}
		return result.substring(0, result.length()-2) + "]";
	}
	
	@RequestMapping(value="/active", produces = "application/json")
	public @ResponseBody String setActive(@RequestParam("id") int id, @RequestParam("active") boolean active){
		return Integer.toString(skillDao.setActive(id, active));
	}
}
