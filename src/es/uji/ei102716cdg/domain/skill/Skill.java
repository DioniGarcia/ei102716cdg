package es.uji.ei102716cdg.domain.skill;

/**
 * Representa una skill asociada a un nivel concreto.
 * Por ejemplo Matem�ticas Medio.
 * 
 * La tarea de crear Skills esta reservada al PC
 */

public class Skill {
	
	private int 	skill_id;		//Identificador �nico en la base de datos
	private String 	name;			//Nombre de la skill
	private String 	description;	//Breve descripci�n de la skill en dicho nivel
	private Level  	level;			//Nivel de dificultad (Iniciado, Medio, Experto)
	private boolean active;			//Indica si la skill debe aparecer en el sistema para ese nivel concreto
	
	//Constructores
	public Skill(){}
	
	public Skill(String name, String description, Level level, boolean active) {
		this.name = name;
		this.description = description;
		this.level  = level;
		this.active = active;
	}
	
	//Getters y Setters
	public int getSkill_id() {
		return skill_id;
	}
	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
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
		return level.name();
	}
	public void setLevel(String levelName) {
		switch (levelName) {
		case "Iniciado":
			this.level = Level.Iniciado;
			break;

		case "Medio":
			this.level = Level.Medio;
			break;
		
		case "Experto":
			this.level = Level.Experto;
			break;
			
		default:
			//Seria conveniente a�adir algun tipo de excepci�n
			break;
		}
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	//To String
	@Override
	public String toString() {
		return "Skill [skill_id=" + skill_id + ", name=" + name + ", description=" + description + ", level=" + level
				+ ", active=" + active + "]";
	}
	
}//Fin