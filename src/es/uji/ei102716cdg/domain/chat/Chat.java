package es.uji.ei102716cdg.domain.chat;

public class Chat {

	private int chat_id;			//Identificador del chat
	private String nick_user_one;	//Nombre del primer usuario participante del chat, alfabeticamente
	private String nick_user_two;	//Nombre del segundo usuario participante del chat, alfabeticamente
	
	//Getter & Setter
	public int getChat_id() {
		return chat_id;
	}
	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}
	public String getNick_user_one() {
		return nick_user_one;
	}
	public void setNick_user_one(String nick_user_one) {
		this.nick_user_one = nick_user_one;
	}
	public String getNick_user_two() {
		return nick_user_two;
	}
	public void setNick_user_two(String nick_user_two) {
		this.nick_user_two = nick_user_two;
	}

	//To String
	@Override
	public String toString() {
		return "Chat [chat_id=" + chat_id + ", nick_user_one=" + nick_user_one + ", nick_user_two=" + nick_user_two
				+ "]";
	}
	
}
