package es.uji.ei102716cdg.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei102716cdg.service.PointsServiceInterface;
import es.uji.ei102716cdg.service.StatsServiceInterface;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private StatsServiceInterface statsService;
	
	@Autowired
	public void setStatsService(StatsServiceInterface statsService){
		this.statsService = statsService;
	}
	
	@RequestMapping("/index")
	public String listSkills(Model model){
		return "admin";
	}
	
	@RequestMapping("/stats")
	public String getStats(Model model){
		model.addAttribute("numeroUsuarios",statsService.getNumeroUsuarios());
		model.addAttribute("mediaPuntos", statsService.getMediaPuntos());
		return "stats";
	}
}
