package es.uji.ei102716cdg.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.uji.ei102716cdg.service.StatsServiceInterface;
import es.uji.ei102716cdg.util.Encoding;

@Controller
@RequestMapping("api/stats")
public class StatsApi {

	@Autowired
	private StatsServiceInterface statsService;
	
	@RequestMapping(value="/hotSkills", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String hotSkills(){
		String result = "";
		String skillName = "";
		String num = "";
		List<String> hotSkills = statsService.getHotSkills();
		if (hotSkills.isEmpty()){
			hotSkills.add("Vacío#1");
		}
		result += "[";
		result += "{\"type\": \"pie\","
				+ "\"name\": \"HotSkills\","
				+ "\"showInLegend\": true,"
				+ "\"dataPoints\": [";
		for (int i = 0; i < hotSkills.size() - 1; i++){
			String[] tupla = hotSkills.get(i).split("#");
			skillName = tupla[0];
			num = tupla[1];
			result += "{\"name\":\"" + Encoding.convertUTF8ToLatin(skillName) + "\", \"y\": \"" + num + "\"}, \n ";
		}
		String[] tupla = hotSkills.get(hotSkills.size()-1).split("#");
		skillName = tupla[0];
		num = tupla[1];
		result += "{\"name\":\"" + Encoding.convertUTF8ToLatin(skillName) + "\", \"y\": \"" + num + "\"} \n ] } ]";
		return result;
	}
	
	@RequestMapping(value="/coldSkills", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String coldSkills(){
		String result = "";
		String skillName = "";
		String num = "";
		List<String> coldSkills = statsService.getColdSkills();
		if (coldSkills.isEmpty()){
			coldSkills.add("Vacío#1");
		}
		result += "[";
		result += "{\"type\": \"pie\","
				+ "\"name\": \"ColdSkills\","
				+ "\"showInLegend\": true,"
				+ "\"dataPoints\": [";
		for (int i = 0; i < coldSkills.size() - 1; i++){
			String[] tupla = coldSkills.get(i).split("#");
			skillName = tupla[0];
			num = tupla[1];
			result += "{\"name\":\"" + Encoding.convertUTF8ToLatin(skillName) + "\", \"y\": \"" + num + "\"}, \n ";
		}
		String[] tupla = coldSkills.get(coldSkills.size()-1).split("#");
		skillName = tupla[0];
		num = tupla[1];
		result += "{\"name\":\"" + Encoding.convertUTF8ToLatin(skillName) + "\", \"y\": \"" + num + "\"} \n ] } ]";
		return result;
	}
	
	
	@RequestMapping(value="/postStats", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String postStats(){
		String result = "";
		int[] postStats = statsService.getPostStats();
		result += "[";
		result += "{\"type\": \"pie\","
				+ "\"name\": \"Ofertas\","
				+ "\"legendText\": \"{indexLabel}\","
				+ "\"showInLegend\": true,"
				+ "\"dataPoints\": [";
		result += "{\"indexLabel\":\"ofertas\", \"y\": \"" + postStats[0] + "\"}, \n "
				+ "{\"indexLabel\":\"demandas\", \"y\": \"" + postStats[1] + "\"} \n ] } ]";
		return result;
	}
	
	
	@RequestMapping(value="/columns", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String skillNames(){
		String result = "";
		String[] meses = 	{"Septiembre", "Octubre", "Noviembre", "Diciembre",
				 			 "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio"};
		int[][] stats = statsService.getGeneralStats();
		result += "[";
		result += "{\"type\": \"column\","
				+ "\"name\": \"Ofertas\","
				+ "\"legendText\": \"Ofertas\","
				+ "\"showInLegend\": true,"
				+ "\"dataPoints\": [";
		for ( int j = 0; j < 10 ; j++){
			result += "{\"label\": \"" + meses[j]  +"\", \"y\": " + stats[0][j] + "}, \n";
		}
		result += "{\"label\": \"" + meses[10]  +"\", \"y\": " + stats[0][10] + "} ] },";
		
		result += "{\"type\": \"column\","
				+ "\"name\": \"Colaboraciones\","
				+ "\"legendText\": \"Colaboraciones\","
				+ "\"showInLegend\": true,"
				+ "\"dataPoints\": [";
		for ( int j = 0; j < 10 ; j++){
			result += "{\"label\": \"" + meses[j]  +"\", \"y\": " + stats[1][j] + "}, \n";
		}
		result += "{\"label\": \"" + meses[10]  +"\", \"y\": " + stats[1][10] + "} ] },";
		
		result += "{\"type\": \"column\","
				+ "\"name\": \"Demandes\","
				+ "\"legendText\": \"Demandas\","
				+ "\"showInLegend\": true,"
				+ "\"dataPoints\": [";
		for ( int j = 0; j < 10 ; j++){
			result += "{\"label\": \"" + meses[j]  +"\", \"y\": " + stats[2][j] + "}, \n";
		}
		result += "{\"label\": \"" + meses[10]  +"\", \"y\": " + stats[2][10] + "} ] }  ]";
		
		
		return result;
	}
	
	
}
