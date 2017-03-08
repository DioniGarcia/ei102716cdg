package es.uji.ei102716cdg.domain;

import java.sql.Date;

public class Offer {
	
	private int id;
	private Date startDate;
	private Date endDate;
	private String description;
	private String nif;
	private int skill_id;
	
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
	public int getSkill_id() {
		return skill_id;
	}
	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
	}
	
	@Override
	public String toString() {
		return "Request [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", description="
				+ description + ", nif=" + nif + ", skill_id=" + skill_id + "]";
	}
}
