package es.uji.ei102716cdg.controller.chat;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.uji.ei102716cdg.domain.chat.Chat;
import es.uji.ei102716cdg.domain.chat.Message;
import es.uji.ei102716cdg.domain.user.User;
import es.uji.ei102716cdg.service.ChatService;


@Controller
@RequestMapping("chat")
public class MyChatController {

	private ChatService chatService;

	@Autowired
	public void setPostService(ChatService chatService){
		this.chatService = chatService;
	}
	
	@RequestMapping("new")
	public String newChat(Model model, HttpSession session, @RequestParam("with") String with){
		
		String nick= ((User) session.getAttribute("user")).getNick();
		
		int chatId = chatService.newChat(nick, with);
		
		model.addAttribute("chats", chatService.getMyChats(nick));
		return "redirect:" + chatId;
	}
	
	
	@RequestMapping("")
	public String listChats(Model model, HttpSession session){
		
		String nick= ((User) session.getAttribute("user")).getNick();
		
		List<Chat> chats = chatService.getMyChats(nick);
		
		model.addAttribute("chats", chats);
		model.addAttribute("chatUnread", chatService.getNumberUnreadMessages(chats, nick));
		model.addAttribute("chatIndex", true);
		return "chat";
	}
	
	@RequestMapping("/{id}")
	public String getChat(Model model, HttpSession session, @PathVariable int id){
		
		String nick= ((User) session.getAttribute("user")).getNick();
		chatService.setUnreadMessages(nick, id);
		List<Chat> chats = chatService.getMyChats(nick);
		
		model.addAttribute("chats", chats);
		model.addAttribute("chatUnread", chatService.getNumberUnreadMessages(chats, nick));
		model.addAttribute("messages", chatService.getMessages(id));
		model.addAttribute("nick", nick);
		model.addAttribute("activeChat", id);
		return "chat";
	}
	
	@RequestMapping(value="/{id}/send", method=RequestMethod.POST, produces = "application/json")
	public @ResponseBody String addMessage(HttpSession session, @PathVariable int id, @RequestParam("content") String content){
		
		String nick= ((User) session.getAttribute("user")).getNick();
		
		Message message = new Message();
		message.setChatId(id);
		message.setContent(content);
		message.setSenderNick(nick);
		chatService.sendMessage(message);
		
		return "1";
	}
}
