package es.uji.ei102716cdg.domain.collaboration;

/**
 * <h1>Representa una oferta de ayuda por parte de un estudiante</h1>
 * Cada cliente puede publicar tantas ofertas como quiera
 */

public class Offer extends Post{
	
	//To String
	@Override
	public String toString() {
		return "Offer [id=" + getId() + ", student_nick=" + getStudent_nick() + ", skillId=" + getSkill_Id() + ", startDate=" + getStartDate()
				+ ", endDate=" + getEndDate() + ", description=" + getDescription() + ", active=" + isActive() + "]";
	}
	
}