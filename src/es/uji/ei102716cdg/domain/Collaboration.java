package es.uji.ei102716cdg.domain;

import java.sql.Date;

public class Collaboration {
	private int id;
	private Date startDate;
	private Date endDate;
	private short totalHours;
	private String comments;
	private short rating;
	private int offerId;
	private int requestId;
	
	
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
