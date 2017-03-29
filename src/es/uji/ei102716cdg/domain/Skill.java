package es.uji.ei102716cdg.domain;

/**
 * <h1>Representa un tipo de habilidad en un nivel concreto</h1>
 * Los tipos de habilidad son creados por un miembro del consejo estudiantil, el PC  
 */

public class Skill {
	private int id;					//Identificador unico del tipo de habilidad
	private String  name;			//Nombre del tipo de hablilidad, Matematicas por ejemplo
	private String  description;	//Pequenya descripcion para facilitar la comprension del tipo de habilidad para el nivel concreto
	private String  level;			//Nivel de dificultad (Iniciado, Medio, Experto)
	private boolean status;			//Estado actual del tipo de habilidad en el sistema (Activo o Inactivo)
	
	public Skill(){}
	
	public Skill(String name, String description, String level, boolean status) {
		super();
		this.name = name;
		this.description = description;
		this.level = level;
		this.status = status;
	}
	//GETTERS & SETTERS
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
	