package es.uji.ei102716cdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei102716cdg.dao.MessageDao;
import es.uji.ei102716cdg.domain.Message;

@Controller
@RequestMapping("/message")
public class MessageController {
private MessageDao messageDao;
	
	@Autowired
	public void setMessageDao(MessageDao messageDao){
		this.messageDao=messageDao;
	}
	
	@RequestMapping("/list")
	public String listMessages(Model model){
		model.addAttribute("messages", messageDao.getMessages());
		return "message/list";
	}
	
	@RequestMapping("/add")
	public String addMessage(Model model){
		model.addAttribute("message",new Message());
		return "message/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("message") Message message,
													BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return "message/add";
		messageDao.addMessage(message);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editMessage(Model model, @PathVariable int id){
		model.addAttribute("message", messageDao.getMessage(id));
		return "message/update";
	}
	
	@RequestMapping(value="/update/{userName}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String userName,
								@ModelAttribute("message") Message message,
								BindingResult bindingResult){
		if (bindingResult.hasErrors())
			return "message/update";
		messageDao.updateMessage(message);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable int id){
		messageDao.deleteMessage(id);
		return "redirect:../list.html";
	}
}
