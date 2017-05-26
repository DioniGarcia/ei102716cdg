package es.uji.ei102716cdg.domain.chat;

import java.sql.Timestamp;

public class Message {

	private int message_id;		//Identificador unico del mensaje
	private int chat_id; 		//Id del chat al que pertenece el mensaje
	private String sender_nick;	//Nick del estudiante que envia el mensaje
	private String content;		//Contenido del mensaje
	private Timestamp sendingDate;	//Fecha en la que fue enviado el mensaje
	private boolean active;		//True si ha sido leido, False si no
	
	//Getters & Setters
	public int getMessageId() {
		return message_id;
	}
	public void setMessageId(int message_id) {
		this.message_id = message_id;
	}
	public int getChatId() {
		return chat_id;
	}
	public void setChatId(int chat_id) {
		this.chat_id = chat_id;
	}
	public String getSenderNick() {
		return sender_nick;
	}
	public void setSenderNick(String sender_nick) {
		this.sender_nick = sender_nick;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getSendingDate() {
		return sendingDate;
	}
	public void setSendingDate(Timestamp sendingDate) {
		this.sendingDate = sendingDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	//To String
	@Override
	public String toString() {
		return "Message [message_id=" + message_id + ", chat_id=" + chat_id + ", sender_nick=" + sender_nick
				+ ", content=" + content + ", sendingDate=" + sendingDate + ", active=" + active + "]";
	}
	
}