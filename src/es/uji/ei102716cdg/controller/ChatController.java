package es.uji.ei102716cdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.ChatDao;
import es.uji.ei102716cdg.domain.Chat;

@Controller
@RequestMapping("/chat")
public class ChatController {
private ChatDao chatDao;
	
	@Autowired
	public void setChatDao(ChatDao chatDao){
		this.chatDao=chatDao;
	}
	
	@RequestMapping("/list")
	public String listChats(Model model){
		model.addAttribute("chats", chatDao.getChats());
		return "chat/list";
	}
	
	@RequestMapping("/add")
	public String addChat(Model model){
		model.addAttribute("chat",new Chat());
		return "chat/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("chat") Chat chat,
													BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "chat/add";
		chatDao.addChat(chat);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editChat(Model model, @PathVariable int id){
		model.addAttribute("chat", chatDao.getChat(id));
		return "chat/update";
	}
	
	@RequestMapping(value="/update/{userName}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String userName,
								@ModelAttribute("chat") Chat chat,
								BindingResult bindingResult){
		if (bindingResult.hasErrors())
			return "chat/update";
		chatDao.updateChat(chat);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable int id){
		chatDao.deleteChat(id);
		return "redirect:../list.html";
	}
}
