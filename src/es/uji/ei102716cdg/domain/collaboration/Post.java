package es.uji.ei102716cdg.domain.collaboration;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * <h1>Representa una oferta de ayuda por parte de un estudiante</h1>
 * Cada cliente puede publicar tantas ofertas como quiera
 */

public class Post {
	
	int  id;				//Identificador unico de la oferta
	String student_nick;	//Identificador del estudiante que realiza el post
	int  skill_Id;			//Identificador del Tipo de habilidad al que se refiere la oferta 
  @DateTimeFormat(pattern = "dd/MM/yyyy")
	Date startDate;			//Fecha de inicio de una posible colaboracion 
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	Date endDate;			//Fecha limite para la colaboracion
	String description;		//Pequeña descripcion complementaria
	boolean active;			//Estado del Post (Activo o Inactivo)
	
	public Post() {};
	
	public Post(String student_nick, int skill_Id, Date startDate,
			Date endDate, String description, boolean active) {
		super();
		this.student_nick = student_nick;
		this.skill_Id = skill_Id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.active = active;
	}
	//GETTERS & SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudent_nick() {
		return student_nick;
	}
	public void setStudent_nick(String student_nick) {
		this.student_nick = student_nick;
	}
	public int getSkill_Id() {
		return skill_Id;
	}
	public void setSkill_Id(int skillId) {
		this.skill_Id = skillId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isBeforeToday(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new java.sql.Date(new java.util.Date().getTime()));
		String date2 = formatter.format(startDate);
		return date2.compareTo(today) < 0;	
	}
	
	//To String
	@Override
	public String toString() {
		return "Post [id=" + id + ", student_nick=" + student_nick + ", skillId=" + skill_Id + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", description=" + description + ", active=" + active + "]";
	}
	
}