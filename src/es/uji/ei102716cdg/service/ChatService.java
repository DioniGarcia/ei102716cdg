package es.uji.ei102716cdg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uji.ei102716cdg.dao.ChatDao;
import es.uji.ei102716cdg.dao.MessageDao;
import es.uji.ei102716cdg.dao.UnreadMessageDao;
import es.uji.ei102716cdg.domain.chat.Chat;
import es.uji.ei102716cdg.domain.chat.Message;

@Service
public class ChatService {
	
	@Autowired
	private ChatDao chatDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private UnreadMessageDao unreadMessageDao;
	
	public int newChat(String userOne, String userTwo){
		List<Chat> chats = chatDao.getChats();
		
		for (Chat chat : chats){
			if (	(chat.getNickUserOne().equals(userOne) || chat.getNickUserOne().equals(userTwo)) 		&&
					(chat.getNickUserTwo().equals(userOne) || chat.getNickUserTwo().equals(userTwo)))
				return chat.getChatId();
		}

		Chat chat = new Chat();
		chat.setNickUserOne(userOne);
		chat.setNickUserTwo(userTwo);
		return chatDao.addChatAndReturnId(chat);
	}
	
	/**Devuelve todos los mensajes correspondientes a un chat
	 * 
	 * @param chatID
	 * @return mensajes del chat
	 */
	public List<Message> getMessages(int chatId){
		List<Message> messages = messageDao.getMessages();
		
		List<Message> chatMessages = new ArrayList<Message>();
		
		for (Message message : messages){
			if (message.getChatId() == chatId)
				chatMessages.add(message);
		}
		
		return chatMessages;
	}
	
	/**Envia un mensaje
	 * 
	 * @param message
	 */
	public void sendMessage(Message message){
		messageDao.addMessage(message);
	}
	
	
	public List<Chat> getMyChats(String nick){
		List<Chat> myChats = new ArrayList<Chat>();
		
		List<Chat> chats = chatDao.getChats();
		for (Chat chat : chats){
			if (chat.getNickUserOne().equals(nick) || chat.getNickUserTwo().equals(nick))
				myChats.add(chat);
		}
		
		return myChats;
	}
	
	
	public int getNumberUnreadMessages(String nick) {
		return unreadMessageDao.getUnreadMessages(nick).size();
	}
	
	public List<Integer> getNumberUnreadMessages(List<Chat> chats, String nick) {
		List<Integer> listUnread = new ArrayList<Integer>();
		List<Message> messages = unreadMessageDao.getUnreadMessages(nick);
		
		for (Chat chat : chats){
			int suma = 0;
			for (Message message : messages ){
				if (message.getChatId() == chat.getChatId())
					suma++;
			}
			listUnread.add(suma);
		}
		
		return listUnread;
	}
	
	public void setUnreadMessages(String nick, int chatId){
		unreadMessageDao.setReadMessages(nick, chatId);
	}
	

}
