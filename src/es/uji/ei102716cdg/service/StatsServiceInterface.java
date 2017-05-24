package es.uji.ei102716cdg.service;

import java.util.List;

public interface StatsServiceInterface {
	public int[] getPostStats();

	public int[][] getGeneralStats();

	public List<String> getHotSkills();

	public List<String> getColdSkills();

	public Integer getNumeroUsuarios();

	public Integer getMediaPuntos();
}
