package es.uji.ei102716cdg.service;

public interface PointsServiceInterface {
	
	public int getUserPoints(String nick);
	public int getReceivedHours(String nick);
	public int getOfferedHours(String nick);
}
