package es.uji.ei102716cdg.domain.skill;

/**
 * <h1>Representa el conjunto de datos necesarios para construir una habilidad en todos sus niveles</h1>
 * Los datos almacenados se utilizarán para crear desde uno hasta los tres niveles de la skill
 */

public class SkillWrapper {
	private String    name;							//Nombre de la habilidad
	private String[]  descriptionList;				//Descripciones de los distintos niveles
	private boolean[] activeList;					//Estado (activo o inactivo) para los distintos niveles
	private String[]  levelList; 					//Listado de los posibles niveles
	private Skill[]   skills; 						//Listado con una Skill con el mismo nombre para cada nivel existente 
	
	//Constructores
	public SkillWrapper() {
		Level[] levels = Level.values();
		int length = levels.length;
		
		skills = new Skill[length];	
		descriptionList= new String[length];
		activeList = new boolean[length];
		levelList = new String[length];
		
		for(int i=0; i<length; i++){
			levelList[i] = levels[i].getName();
		}
	}//Fin Constructores

	public SkillWrapper(Skill[] skills){
		for(int i=0; i<skills.length; i++){
			Skill skill = skills[i];
			descriptionList[i] = skill.getDescription();
			activeList[i] = skill.isActive();
			levelList[i] = skill.getLevel().toString();
		}
		
		this.skills = skills;
	}
	
	
	//GETTERS & SETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getDescriptionList() {
		return descriptionList;
	}

	public void setDescriptionList(String[] descriptionList) {
		this.descriptionList = descriptionList;
	}

	public boolean[] getActiveList() {
		return activeList;
	}

	public void setActiveList(boolean[] activeList) {
		this.activeList = activeList;
	}

	public String[] getLevelList() {
		return levelList;
	}

	public void setLevelList(String[] levelList) {
		this.levelList = levelList;
	}

	public Skill[] getSkills() {
		for(int i=0; i<skills.length; i++){
			skills[i] = new Skill(name, descriptionList[i], Level.values()[i], activeList[i]);
		}
		return skills;
	}

	public void setSkills(Skill[] skills) {
		this.skills = skills;
	}
	
}
