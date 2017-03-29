package es.uji.ei102716cdg.domain;
/**
 * <h1>Representa el conjunto de datos necesarios para construir una habilidad en todos sus niveles</h1>
 * Los datos almacenados se utilizarán para crear desde uno hasta los tres niveles de la skill
 */

public class SkillWrapper {
	private int		id;					//Identificador unico del tipo de habilidad
	private String  name;				//Nombre del tipo de hablilidad, Matematicas por ejemplo
	private boolean statusIniciado;		//Identifica si el nivel Iniciado esta activado
	private String  descIniciado;		//Descripcion del nivel de dificultad Iniciado
	private boolean statusMedio;		//Identifica si el nivel Iniciado esta activado
	private String  descMedio;			//Descripcion del nivel de dificultad Medio
	private boolean statusExperto;		//Identifica si el nivel Iniciado esta activado
	private String  descExperto;		//Descripcion del nivel de dificultad Experto
	
	public Skill[] getSkills(){
		Skill[]   skills = new Skill[3];
		String[]  niveles = {"Iniciado", "Medio", "Experto"};
		String[]  descripciones = {descIniciado, descMedio, descExperto};
		Boolean[] status = {statusIniciado, statusMedio, statusExperto};
		for(int i=0; i< skills.length; i++){
			skills[i] = new Skill(name, descripciones[i], niveles[i], status[i]);
		}
		
		return skills;
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
	public String getDescIniciado() {
		return descIniciado;
	}
	public void setDescIniciado(String description) {
		this.descIniciado = description;
	}
	public String getDescMedio() {
		return descMedio;
	}
	public void setDescMedio(String description) {
		this.descMedio = description;
	}
	public String getDescExperto() {
		return descExperto;
	}
	public void setDescExperto(String description) {
		this.descExperto = description;
	}

	public boolean getStatusIniciado() {
		return statusIniciado;
	}
	public void setStatusIniciado(boolean status_iniciado) {
		this.statusIniciado = status_iniciado;
	}
	public boolean getStatusMedio() {
		return statusMedio;
	}
	public void setStatusMedio(boolean status_medio) {
		this.statusMedio = status_medio;
	}
	public boolean getStatusExperto() {
		return statusExperto;
	}
	public void setStatusExperto(boolean status_experto) {
		this.statusExperto = status_experto;
	}
	
	@Override
	public String toString() {
		return "SkillWrapper [id=" + id + ", name=" + name + ", desc_iniciado=" + descIniciado + ", desc_medio="
				+ descMedio + ", desc_experto=" + descExperto + "]";
	}

	
}
