package es.uji.ei102716cdg.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei102716cdg.service.PostServiceInterface;

@Controller
@RequestMapping("admin")
public class AdminController {

	private PostServiceInterface postService;
	
	@Autowired
	public void setPostService(PostServiceInterface postService){
		this.postService = postService;
	}
	
	@RequestMapping("/index")
	public String listSkills(Model model){
		return "admin";
	}
	
	@RequestMapping("/stats")
	public String getStats(Model model){
		model.addAttribute("numeroUsuarios",postService.getNumeroUsuarios());
		model.addAttribute("mediaPuntos", postService.getMediaPuntos());
		return "stats";
	}
}
