package es.uji.ei102716cdg.domain;

import java.sql.Timestamp;

public class Chat {
	private int id;
	private String senderNif;
	private String receiverNif; 
	private Timestamp createdAt;
	private int messageId;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getSenderNif() {
		return senderNif;
	}



	public void setSenderNif(String senderNif) {
		this.senderNif = senderNif;
	}



	public String getReceiverNif() {
		return receiverNif;
	}



	public void setReceiverNif(String receiverNif) {
		this.receiverNif = receiverNif;
	}



	public Timestamp getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}



	public int getMessageId() {
		return messageId;
	}



	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}



	@Override
	public String toString() {
		return "Chat [id=" + id + ", senderNif=" + senderNif + ", receiverNif=" + receiverNif + ", createdAt="
				+ createdAt + ", messageId=" + messageId + "]";
	}
	
	
}
