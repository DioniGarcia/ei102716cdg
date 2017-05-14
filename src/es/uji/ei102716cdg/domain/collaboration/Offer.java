package es.uji.ei102716cdg.domain.collaboration;

import java.sql.Date;

/**
 * <h1>Representa una oferta de ayuda por parte de un estudiante</h1>
 * Cada cliente puede publicar tantas ofertas como quiera
 */

public class Offer extends Post{
	
	
	
	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Offer(String student_nick, int skill_Id, Date startDate,
			Date endDate, String description, boolean active) {
		super(student_nick, skill_Id, startDate, endDate, description, active);
		// TODO Auto-generated constructor stub
	}

	//To String
	@Override
	public String toString() {
		return "Offer [id=" + id + ", student_nick=" + student_nick + ", skillId=" + skill_Id + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", description=" + description + ", active=" + active + "]";
	}
	
}