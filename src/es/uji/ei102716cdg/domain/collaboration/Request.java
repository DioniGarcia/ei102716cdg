package es.uji.ei102716cdg.domain.collaboration;

import java.sql.Date;

/**
 * <h1>Representa una demanda de ayuda por parte de un estudiante</h1>
 * Cada cliente puede publicar tantas demandas como quiera siempre que tenga puntos para ello
 */

public class Request extends Post{
	
	
	
	public Request() {
		super();
	}

	public Request(String student_nick, int skill_Id, Date startDate,
			Date endDate, String description, boolean active) {
		super(student_nick, skill_Id, startDate, endDate, description, active);
	}

	//To String
	@Override
	public String toString() {
		return "Request [id=" + id + ", student_nick=" + student_nick + ", skillId=" + skill_Id + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", description=" + description + ", active=" + active + "]";
	}
	
}