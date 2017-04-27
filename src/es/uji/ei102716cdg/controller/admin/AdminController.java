package es.uji.ei102716cdg.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei102716cdg.domain.collaboration.Offer;

@Controller
@RequestMapping("admin")
public class AdminController {

	
	@RequestMapping("/index")
	public String addOffer(Model model){
		return "admin";
	}
}
