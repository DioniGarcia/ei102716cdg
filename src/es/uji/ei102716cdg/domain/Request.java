package es.uji.ei102716cdg.domain;

import java.sql.Date;

public class Request {
	
	
	private int id;
	private Date startDate;
	private Date endDate;
	private String description;
	private String nif;
	private int skillId;
	
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

