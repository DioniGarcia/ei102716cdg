package es.uji.ei102716cdg.dao;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.Student;

import java.util.List;

import java.sql.SQLException;
import java.sql.ResultSet;


@Repository
public class StudentDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	private static final class StudentMapper implements RowMapper<Student>{
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			student.setNif(rs.getString("nif"));
			student.setName(rs.getString("name"));
			student.setEmail(rs.getString("email"));
			student.setUserName(rs.getString("userName"));
			student.setPasswd(rs.getString("passwd"));
			return student;
		}
	}

	public List<Student> getStudents() {
		return this.jdbcTemplate.query("select nif, name, email, userName, passwd from Student",
				new StudentMapper());
	}
	

	public Student getStudent(String userName) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM Student WHERE userName = ?",
				new Object[] {userName}, new StudentMapper());

	}
	
	public void addStudent(Student student) {
		this.jdbcTemplate.update("insert into Student(nif, name, email, userName, passwd) "
				+ "values(?, ?, ?, ?, ?)",
				student.getNif(), student.getName(), student.getEmail() , student.getUserName(), student.getPasswd());
	}

	public void updateStudent(Student student) {
		this.jdbcTemplate.update("update student "
				+ "set nif = ?,"
				+ "name = ?,"
				+ "email = ?,"
				+ "userName = ?"
				+ "passwd = ?"
				+ "where nif = ?",
				student.getNif(), student.getName(), student.getEmail() , student.getUserName(), student.getPasswd(), student.getNif());
	}

	public void deleteStudent(String userName) {
		this.jdbcTemplate.update("DELETE FROM Student WHERE userName = ?", userName);
	}
}
