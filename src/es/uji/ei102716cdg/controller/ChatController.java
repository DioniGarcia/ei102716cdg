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
import es.uji.ei102716cdg.domain.chat.Chat;
import es.uji.ei102716cdg.service.PostServiceInterface;
import es.uji.ei102716cdg.validator.ChatValidator;


@Controller
@RequestMapping("db_test/chat")
public class ChatController {
	private ChatDao chatDao;
	private PostServiceInterface postService;

	@Autowired
	public void setPostService(PostServiceInterface postService){
		this.postService = postService;
	}
	
	@Autowired
	public void setChatDao(ChatDao chatDao){
		this.chatDao=chatDao;
	}
	
	@RequestMapping("/list")
	public String listChats(Model model){
		model.addAttribute("chats", chatDao.getChats());
		return "db_test/chat/list";
	}
	
	@RequestMapping("/add")
	public String addChat(Model model){
		model.addAttribute("chat",new Chat());
		model.addAttribute("nick_list", postService.getActiveStudentsNick());
		return "db_test/chat/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("chat") Chat chat,
													BindingResult bindingResult){
		ChatValidator chatValidator = new ChatValidator();
		chatValidator.validate(chat, bindingResult);
		if(bindingResult.hasErrors())
			return "db_test/chat/add";
		chatDao.addChat(chat);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{chat_id}", method=RequestMethod.GET)
	public String editStudent(Model model, @PathVariable int chat_id){
		model.addAttribute("chat", chatDao.getChat(chat_id));
		model.addAttribute("nick_list", postService.getActiveStudentsNick());
		return "db_test/chat/update";
	}
	
	@RequestMapping(value="/update/{chat_id}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable int chat_id,
								@ModelAttribute("chat") Chat chat,
								BindingResult bindingResult){
		ChatValidator chatValidator = new ChatValidator();
		chatValidator.validate(chat, bindingResult);
		if (bindingResult.hasErrors())
			return "db_test/chat/update";
		chatDao.updateChat(chat);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{chat_id}")
	public String processDelete(@PathVariable int chat_id){
		chatDao.deleteChat(chat_id);
		return "redirect:../list.html";
	}
	
}