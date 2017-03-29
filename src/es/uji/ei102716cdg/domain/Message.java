package es.uji.ei102716cdg.domain;

public class Message {
	private int id;
	private String msg;
	private short status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", msg=" + msg + ", status=" + status + "]";
	}
	
	
}
