package es.uji.ei102716cdg.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

	
	@RequestMapping("/index")
	public String listSkills(Model model){
		return "admin";
	}
	
	@RequestMapping("/stats")
	public String getStats(Model model){
		return "stats";
	}
}
