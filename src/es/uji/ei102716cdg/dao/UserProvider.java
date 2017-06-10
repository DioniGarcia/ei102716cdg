package es.uji.ei102716cdg.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.user.User;

@Repository
public class UserProvider implements UserDao, AdminDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private static final class UserMapper implements RowMapper<User>{
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setNick(rs.getString("nick"));
			user.setPasswd(rs.getString("passwd"));
			return user;
		}
	}
	
	@Override
	public User loadUserByUsername(String username, String password) {
		// Get user from the database
		User user = getUserFromDatabase(username);
		// Check if the user is in the database
		if ( user == null ){
			return null; 
		}
		// If it is, check password
		if (passwordEncoder().matches(password, user.getPasswd())){
		//if ( password.equals( user.getPasswd() ) ){
			user.setPasswd("");
			return user;
		} else {
			return null;
		}
		
	}
	
	@Override
	public User loadAdminByUsername(String username, String password) {
		// Get user from the database
		User user = getAdminFromDatabase(username);
		// Check if the user is in the database
		if ( user == null ){
			return null; 
		}
		// If it is, check password
		//if (passwordEncoder().matches(password, user.getPasswd())){
		if ( password.equals( user.getPasswd() ) ){
			user.setPasswd("");
			return user;
		} else {
			return null;
		}
		
	}

	public User getUserFromDatabase(String username){
		return this.jdbcTemplate.queryForObject("SELECT * FROM Student WHERE nick = ?",
				new Object[] {username}, new UserMapper());
	}
	
	public User getAdminFromDatabase(String username){
		return this.jdbcTemplate.queryForObject("SELECT * FROM Administrator WHERE nick = ?",
				new Object[] {username}, new UserMapper());
	}
	
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Override
	public String encodePassword(String passwd){
		return passwordEncoder().encode(passwd);
	}
	
	@Override
	public boolean existsUsername(String username) {
		try {
			this.jdbcTemplate.queryForObject("SELECT * FROM Student WHERE nick = ?",
				new Object[] {username}, new UserMapper());
			return true;
		} catch (EmptyResultDataAccessException  e){
			return false;
		}
		
	}
	@Override
	public boolean existsAdmin(String username) {
		try {
			this.jdbcTemplate.queryForObject("SELECT * FROM Administrator WHERE nick = ?",
				new Object[] {username}, new UserMapper());
			return true;
		} catch (EmptyResultDataAccessException  e){
			return false;
		}
		
	}
	
}