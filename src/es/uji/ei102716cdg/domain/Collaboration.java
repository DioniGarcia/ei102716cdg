package es.uji.ei102716cdg.domain;

import java.sql.Date;

/**
 * <h1>Representa la colaboracion entre dos estudiantes</h1>
 */

public class Collaboration {
	private int id;				//Identificador unico de la colaboracion
	private Date startDate;		//Fecha de inicio
	private Date endDate;		//Fecha de fin
	private short totalHours;	//Computo total de horas
	private String comments;	//Comentario escrito por el demandante de la colaboracion
	private short rating;		//Puntuacion por parte del demandante entre 1 y 5
	private int offerId;		//Identificador de la oferta
	private int requestId;		//Identificador del Tipo de habilidad al que se refiere la oferta
	
	
	//GETTERS & SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public short getRating() {
		return rating;
	}
	public void setRating(short rating) {
		this.rating = rating;
	}
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
	@Override
	public String toString() {
		return "Collaboration [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", totalHours="
				+ totalHours + ", comments=" + comments + ", rating=" + rating + ", offerId=" + offerId + ", requestId="
				+ requestId + "]";
	}
}
