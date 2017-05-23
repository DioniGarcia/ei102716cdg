package es.uji.ei102716cdg.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.uji.ei102716cdg.service.PostServiceInterface;

@Controller
@RequestMapping("api/stats")
public class StatsApi {

	private PostServiceInterface postService;
	
	@Autowired
	public void setPostService(PostServiceInterface postService){
		this.postService = postService;
	}
	
	@RequestMapping(value="/hotSkills", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String hotSkills(){
		String result = "";
		String skillName = "";
		String num = "";
		List<String> hotSkills = postService.getHotSkills();
		if (hotSkills.isEmpty()){
			hotSkills.add("Vacío#1");
		}
		result += "[";
		result += "{\"type\": \"pie\","
				+ "\"name\": \"HotSkills\","
				+ "\"legendText\": \"{indexLabel}\","
				+ "\"showInLegend\": true,"
				+ "\"dataPoints\": [";
		for (int i = 0; i < hotSkills.size() - 1; i++){
			String[] tupla = hotSkills.get(i).split("#");
			skillName = tupla[0];
			num = tupla[1];
			result += "{\"indexLabel\":\"" + skillName + "\", \"y\": \"" + num + "\"}, \n ";
		}
		String[] tupla = hotSkills.get(hotSkills.size()-1).split("#");
		skillName = tupla[0];
		num = tupla[1];
		result += "{\"indexLabel\":\"" + skillName + "\", \"y\": \"" + num + "\"} \n ] } ]";
		return result;
	}
	
	@RequestMapping(value="/coldSkills", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String coldSkills(){
		String result = "";
		String skillName = "";
		String num = "";
		List<String> coldSkills = postService.getColdSkills();
		if (coldSkills.isEmpty()){
			coldSkills.add("Vacío#1");
		}
		result += "[";
		result += "{\"type\": \"pie\","
				+ "\"name\": \"ColdSkills\","
				+ "\"legendText\": \"{indexLabel}\","
				+ "\"showInLegend\": true,"
				+ "\"dataPoints\": [";
		for (int i = 0; i < coldSkills.size() - 1; i++){
			String[] tupla = coldSkills.get(i).split("#");
			skillName = tupla[0];
			num = tupla[1];
			result += "{\"indexLabel\":\"" + skillName + "\", \"y\": \"" + num + "\"}, \n ";
		}
		result += "{\"indexLabel\":\"" + skillName + "\", \"y\": \"" + num + "\"} \n ] } ]";
		return result;
	}
	
	
	@RequestMapping(value="/postStats", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String postStats(){
		String result = "";
		int[] postStats = postService.getPostStats();
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
		int[][] stats = postService.getGeneralStats();
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
