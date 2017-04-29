package es.uji.ei102716cdg.domain.user;

import java.sql.Date;

public class Ban {
	private String studentNick;		//Dni identificador del estudiante baneado
	private Date banDate;			//Fecha de inicio del castigo
	private int  days;				//Tiempo desde la fecha de inicio hasta que acaba el castigo, en días. Si es 0 el castigo es indefinido
	private String reason;			//Motivo del baneo que se le muestra al usuario cuando se le informa del baneo
	
	//Getters y Setters
	public String getStudent_nick() {
		return studentNick;
	}
	public void setStudent_nick(String student_nick) {
		this.studentNick = student_nick;
	}
	public Date getBanDate() {
		return banDate;
	}
	public void setBanDate(Date banDate) {
		this.banDate = banDate;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	//To String
	@Override
	public String toString() {
		return "Ban [student_nick=" + studentNick + ", banDate=" + banDate + ", days=" + days + ", reason=" + reason
				+ "]";
	}
	

}
