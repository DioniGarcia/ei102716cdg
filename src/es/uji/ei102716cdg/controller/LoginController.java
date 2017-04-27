package es.uji.ei102716cdg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.UserDao;
import es.uji.ei102716cdg.domain.user.User;


@Controller
public class LoginController {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
	}
	
	@RequestMapping("/login")
	public String login(Model model, HttpSession session) {
		if (session.getAttribute("user") != null)
			return "redirect:indexDbTest.jsp";
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String checkLogin(@ModelAttribute("user") User user,  		
				BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "login";
		}
	       // Comprova que el login siga correcte 
		// intentant carregar les dades de l'usuari 
		if (! userDao.existsUsername(user.getNick())) {
			bindingResult.rejectValue("nick", "userNotFound", "User not found"); 
			return "login";
		}
		
		user = userDao.loadUserByUsername(user.getNick(),user.getPasswd()); 
		
		if (user == null) {
			bindingResult.rejectValue("passwd", "badpw", "Contrasenya incorrecta"); 
			return "login";
		}
		// Autenticats correctament. 
		// Guardem les dades de l'usuari autenticat a la sessio
		session.setAttribute("user", user); 
			
		// Torna a la pagina principal
		return "redirect:indexDbTest.jsp";
	}

	@RequestMapping("/logout") 
	public String logout(HttpSession session) {
		session.invalidate(); 
		return "redirect:indexDbTest.jsp";
	}

}