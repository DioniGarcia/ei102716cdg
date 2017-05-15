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
import es.uji.ei102716cdg.domain.skill.SkillWithStats;
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
	
	@RequestMapping(value="/names", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String skillNames(){
		String result = "";
		List<String> skills = skillDao.skillNames();
		if (skills.isEmpty()) return "[]";
		result += "[";
		for(String skill : skills){
			result += "{\"id\": \"" + skill + "\", "
					+ "\"text\": \"" + skill + "\"},";
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
		StringBuilder res = new StringBuilder(1000);
		List<SkillWithStats> skills = skillDao.getSkillsWithStats();
		res.append("[");
		for(SkillWithStats skill : skills){
			int numberOffers = skill.getNumberOffers();
			int numberRequests = skill.getNumberRequests();
			res.append("{\"id\": \"").append(skill.getSkill_id()).append("\", ");
			res.append("\"name\": \"").append(Encoding.convertUTF8ToLatin(skill.getName())).append("\", ");
			res.append("\"description\": \"").append(Encoding.convertUTF8ToLatin(skill.getDescription())).append("\", ");
			res.append( "\"offers\": \"").append(Encoding.convertUTF8ToLatin(Integer.toString(numberOffers))).append("\", ");
			res.append( "\"requests\": \"").append(Encoding.convertUTF8ToLatin(Integer.toString(numberRequests))).append("\", ");
			res.append("\"level\": \"" ).append(Encoding.convertUTF8ToLatin(skill.getLevel())).append("\", ");
			res.append("\"active\": ").append( (skill.isActive() ? "true" : "false")).append("}, ");
		}
		return res.toString().substring(0, res.toString().length()-2) + "]";
	}
	
	@RequestMapping(value="/active", produces = "application/json")
	public @ResponseBody String setActive(@RequestParam("id") int id, @RequestParam("active") boolean active){
		return Integer.toString(skillDao.setActive(id, active));
	}
	
	@RequestMapping(value="/description", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String setDescription(@RequestParam("id") int id, @RequestParam("description") String description){
		return Integer.toString(skillDao.setDescription(id, description));
	}
	
	@RequestMapping(value="/title", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String setName(@RequestParam("name") String name, @RequestParam("original") String original){
		return Integer.toString(skillDao.setName(original, name));
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String addSkill(	@RequestParam("name") String name, 
											@RequestParam(value="description-1", required = false) String descriptionLow,
											@RequestParam(value="description-2", required = false) String descriptionMed,
											@RequestParam(value="description-3", required = false) String descriptionHigh,
											@RequestParam(value="active-1", required = false) String activeLow,
											@RequestParam(value="active-2", required = false) String activeMed,
											@RequestParam(value="active-3", required = false) String activeHigh){
		String[] description = {descriptionLow, descriptionMed, descriptionHigh};
		String[] active = {activeLow, activeMed, activeHigh};
		String[] levelName = {"Iniciado", "Medio", "Experto"};
		Skill skill;
		for(int i = 0; i<3; i++){
			skill = new Skill();
			skill.setName(name);
			skill.setDescription(description[i]!=null ? description[i] : "");
			skill.setLevel(levelName[i]);
			skill.setActive(active[i] != null);
			skillDao.addSkill(skill);
		}
		return "1";
	}
	
}
