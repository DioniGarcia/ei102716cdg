package es.uji.ei102716cdg.domain.chat;

public class Chat {

	private int chatId;			//Identificador del chat
	private String nickUserOne;	//Nombre del primer usuario participante del chat, alfabeticamente
	private String nickUserTwo;	//Nombre del segundo usuario participante del chat, alfabeticamente
	
	//Getter & Setter
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public String getNickUserOne() {
		return nickUserOne;
	}
	public void setNickUserOne(String nickUserOne) {
		this.nickUserOne = nickUserOne;
	}
	public String getNickUserTwo() {
		return nickUserTwo;
	}
	public void setNickUserTwo(String nickUserOne) {
		this.nickUserTwo = nickUserOne;
	}

	//To String
	@Override
	public String toString() {
		return "Chat [chat_id=" + chatId + ", nick_user_one=" + nickUserOne + ", nick_user_two=" + nickUserTwo
				+ "]";
	}
	
}
