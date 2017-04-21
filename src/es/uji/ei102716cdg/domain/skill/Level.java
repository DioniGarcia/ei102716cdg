package es.uji.ei102716cdg.domain.skill;

public enum Level {
	Iniciado ("Iniciado"),
	Medio    ("Medio"),
	Experto  ("Experto");
	
	private final String name;
	
	private Level(String name) {
		this.name = name;
	}
	
	public String getName(){return name();}

}
