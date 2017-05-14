package es.uji.ei102716cdg.domain.collaboration;

/**
 * <h1>Representa la colaboracion entre dos estudiantes</h1>
 */

public class Collaboration {
	private int collaboration_id;	//Identificador unico de la colaboracion
	private int offer_id;			//Identificador de la oferta
	private int request_id;			//Identificador de la demanda
	private short rating;			//Puntuacion por parte del demandante entre 1 y 5
	private short totalHours;		//Computo total de horas destinadas a la colaboraci�n
	private String comments;		//Comentario escrito por el demandante de la colaboracion
	
	public Collaboration() {}
	
	public Collaboration(int offerId, int requestId){
		this.offer_id = offerId;
		this.request_id = requestId;
		this.rating = 1;
	}
	
	//GETTERS & SETTERS
	public int getCollaboration_id() {
		return collaboration_id;
	}
	public void setCollaboration_id(int collaboration_id) {
		this.collaboration_id = collaboration_id;
	}
	public int getOffer_id() {
		return offer_id;
	}
	public void setOffer_id(int offer_id) {
		this.offer_id = offer_id;
	}
	public int getRequest_id() {
		return request_id;
	}
	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}
	public short getRating() {
		return rating;
	}
	public void setRating(short rating) {
		this.rating = rating;
	}
	public short getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(short totalHours) {
		this.totalHours = totalHours;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	//To String
	@Override
	public String toString() {
		return "Collaboration [collaboration_id=" + collaboration_id + ", offer_id=" + offer_id + ", request_id="
				+ request_id + ", rating=" + rating + ", totalHours=" + totalHours + ", comments=" + comments + "]";
	}
	
}
