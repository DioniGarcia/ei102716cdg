package es.uji.ei102716cdg.controller.my;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uji.ei102716cdg.domain.collaboration.Request;
import es.uji.ei102716cdg.domain.user.User;
import es.uji.ei102716cdg.service.PostServiceInterface;


@Controller
@RequestMapping("my/collaborations")
public class MyCollaborationController {
	
	@Autowired
	private PostServiceInterface postService;
	
	@Autowired
	public void setPostService(PostServiceInterface postService){
		this.postService = postService;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listOffers(Model model, HttpSession session){
		return "my/collaboration/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String listOffers(	@RequestParam(value="confirm", required = false) String confirm,
								@RequestParam(value="offerId", required = false) Integer offerId,
								@RequestParam(value="requestId", required = false) Integer requestId,
								@RequestParam(value="skillId", required = false) Integer skillId,
			Model model, HttpSession session){
		
		if (confirm != null){
			return "redirect:/index.html";
		}
		
		User user = (User) session.getAttribute("user");
		
		if (offerId != null){
			List<Request> requests = postService.getRequestsBySkillId(user.getNick(), skillId);
			model.addAttribute("requests", requests);
		} else {
			
		}
		
		
		return "my/collaboration/add";
	}
	
}
