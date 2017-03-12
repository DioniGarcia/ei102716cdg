package es.uji.ei102716cdg.domain;

import java.sql.Date;

public class Request {
	
	/**
	 * <h1>Representa una demanda de ayuda por parte de un estudiante</h1>
	 * Cada cliente puede publicar tantas demandas como quiera siempre que tenga puntos para ello
	 */
	
	private int id;				//Identificador unico de la demanda
	private Date startDate;		//Fecha de inicio de una posible colaboracion 
	private Date endDate;		//Fecha limite para la colaboracion
	private String description;	//Pequenya descripcion complementaria sobre la ayuda demandada
	private String nif;			//NIF del usuario ofertante
	private int skillId;		//Identificador del Tipo de habilidad al que se refiere la demanda 
	
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

