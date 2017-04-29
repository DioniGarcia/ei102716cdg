package es.uji.ei102716cdg.dao;

import es.uji.ei102716cdg.domain.user.User;

public interface AdminDao {
	public boolean existsAdmin(String username);
	public User loadAdminByUsername(String username, String password);
}
