package es.uji.ei102716cdg.domain.collaboration;

/**
 * <h1>Representa una demanda de ayuda por parte de un estudiante</h1>
 * Cada cliente puede publicar tantas demandas como quiera siempre que tenga puntos para ello
 */

public class Request extends Post{
	
	//To String
	@Override
	public String toString() {
		return "Request [id=" + getId() + ", student_nick=" + getStudent_nick() + ", skillId=" + getSkill_Id() + ", startDate=" + getStartDate()
				+ ", endDate=" + getEndDate() + ", description=" + getDescription() + ", active=" + isActive() + "]";
	}
	
}