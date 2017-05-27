package es.uji.ei102716cdg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.AdminDao;
import es.uji.ei102716cdg.dao.UserDao;
import es.uji.ei102716cdg.domain.user.User;
import es.uji.ei102716cdg.util.Encoding;


@Controller
public class LoginController {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AdminDao adminDao;
	
	
	@Autowired
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
	}
	
	@Autowired
	public void setAdminDao(AdminDao adminDao){
		this.adminDao=adminDao;
	}
	
	@RequestMapping("/login")
	public String login(Model model, HttpSession session) {
		if (session.getAttribute("user") != null)
			return "redirect:index.html";
		model.addAttribute("user", new User());
		return "login/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String checkLogin(@ModelAttribute("user") User user,  		
				BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "login/login";
		}
		
		user.setNick(Encoding.convertLatinToUTF8(user.getNick()));
		user.setPasswd(Encoding.convertLatinToUTF8(user.getPasswd()));
		
	       // Comprova que el login siga correcte 
		// intentant carregar les dades de l'usuari 
		if (! userDao.existsUsername(user.getNick())) {
			if( ! adminDao.existsAdmin(user.getNick())) {
				bindingResult.rejectValue("nick", "userNotFound", "User not found"); 
				return "login/login";
			}
			user = adminDao.loadAdminByUsername(user.getNick(), user.getPasswd());
			if (user == null) {
				bindingResult.rejectValue("passwd", "badpw", "Contraseña incorrecta"); 
				return "login/login";
			}
			
			session.setAttribute("admin", user); 
			
			String nextURL = (String) session.getAttribute("lastURL");
			session.removeAttribute("lastURL");
			if (nextURL != null)
				return "redirect:" + nextURL;
			
			// Torna a la pagina principal
			return "redirect:admin/index.html";
			
			
		}
		
		user = userDao.loadUserByUsername(user.getNick(),user.getPasswd()); 
		
		if (user == null) {
			bindingResult.rejectValue("passwd", "badpw", "Contraseña incorrecta"); 
			return "login/login";
		}
		// Autenticats correctament. 
		// Guardem les dades de l'usuari autenticat a la sessio
		session.setAttribute("user", user); 
		
		String nextURL = (String) session.getAttribute("lastURL");
		session.removeAttribute("lastURL");
		if (nextURL != null)
			return "redirect:" + nextURL;
		
		// Torna a la pagina principal
		return "redirect:index.html";
	}

	@RequestMapping("/logout") 
	public String logout(HttpSession session) {
		session.invalidate(); 
		return "redirect:index.html";
	}

}