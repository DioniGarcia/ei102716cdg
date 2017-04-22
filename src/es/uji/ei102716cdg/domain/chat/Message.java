package es.uji.ei102716cdg.domain.chat;

import java.sql.Date;

public class Message {

	private int message_id;		//Identificador unico del mensaje
	private int chat_id; 		//Id del chat al que pertenece el mensaje
	private String sender_nick;	//Nick del estudiante que envia el mensaje
	private String content;		//Contenido del mensaje
	private Date sendingDate;	//Fecha en la que fue enviado el mensaje
	private boolean active;		//True si ha sido leido, False si no
	
	//Getters & Setters
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	public int getChat_id() {
		return chat_id;
	}
	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}
	public String getSender_nick() {
		return sender_nick;
	}
	public void setSender_nick(String sender_nick) {
		this.sender_nick = sender_nick;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendingDate() {
		return sendingDate;
	}
	public void setSendingDate(Date sendingDate) {
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