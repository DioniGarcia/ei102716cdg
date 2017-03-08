package es.uji.ei102716cdg.domain;

public class Skill {
	private int id;
	private String name;
	private String description;
	private String level;
	private boolean status;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + ", description =" + description + ",level=" + level + ", status ="
				+ status + "]";
	}
	
	
}
	