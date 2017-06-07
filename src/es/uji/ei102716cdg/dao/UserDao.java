package es.uji.ei102716cdg.dao;


import es.uji.ei102716cdg.domain.user.User;

public interface UserDao {
	User loadUserByUsername(String username, String password);
	boolean existsUsername(String username);
	public String encodePassword(String passwd);
}
