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
import es.uji.ei102716cdg.domain.chat.Message;
import es.uji.ei102716cdg.service.PostServiceInterface;
import es.uji.ei102716cdg.validator.MessageValidator;


@Controller
@RequestMapping("db_test/message")
public class MessageController {
	private MessageDao messageDao;
	private PostServiceInterface postService;

	@Autowired
	public void setPostService(PostServiceInterface postService){
		this.postService = postService;
	}
	
	@Autowired
	public void setMessageDao(MessageDao messageDao){
		this.messageDao=messageDao;
	}
	
	@RequestMapping("/list")
	public String listMessages(Model model){
		model.addAttribute("messages", messageDao.getMessages());
		return "db_test/message/list";
	}
	
	@RequestMapping("/add")
	public String addMessage(Model model){
		model.addAttribute("message",new Message());
		model.addAttribute("nick_list", postService.getActiveStudentsNick());
		return "db_test/message/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("message") Message message,
													BindingResult bindingResult){
		MessageValidator messageValidator = new MessageValidator();
		messageValidator.validate(message, bindingResult);
		if(bindingResult.hasErrors())
			return "db_test/message/add";
		messageDao.addMessage(message);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{message_id}", method=RequestMethod.GET)
	public String editStudent(Model model, @PathVariable int message_id){
		model.addAttribute("message", messageDao.getMessage(message_id));
		model.addAttribute("nick_list", postService.getActiveStudentsNick());
		return "db_test/message/update";
	}
	
	@RequestMapping(value="/update/{message_id}", method = RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable int message_id,
								@ModelAttribute("message") Message message,
								BindingResult bindingResult){
		MessageValidator messageValidator = new MessageValidator();
		messageValidator.validate(message, bindingResult);
		if (bindingResult.hasErrors())
			return "db_test/message/update";
		messageDao.updateMessage(message);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{message_id}")
	public String processDelete(@PathVariable int message_id){
		messageDao.deleteMessage(message_id);
		return "redirect:../list.html";
	}
	
}