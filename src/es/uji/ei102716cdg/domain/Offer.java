package es.uji.ei102716cdg.domain;

import java.sql.Date;

/**
 * <h1>Representa una oferta de ayuda por parte de un estudiante</h1>
 * Cada cliente puede publicar tantas ofertas como quiera
 */

public class Offer {
	
	private int id;				//Identificador unico de la oferta
	private Date startDate;		//Fecha de inicio de una posible colaboracion 
	private Date endDate;		//Fecha limite para la colaboracion
	private String description;	//Pequenya descripcion complementaria sobre la ayuda ofrecida
	private String nif;			//NIF del usuario ofertante
	private int skillId;		//Identificador del Tipo de habilidad al que se refiere la oferta 
	
	
	//GETTERS & SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	
	@Override
	public String toString() {
		return "Request [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", description="
				+ description + ", nif=" + nif + ", skill_id=" + skillId + "]";
	}
}
