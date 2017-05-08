package es.uji.ei102716cdg.controller.my;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("my/collaborations")
public class MyCollaborationController {

	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listOffers(Model model, HttpSession session){
		return "my/collaboration/list";
	}
}
