package es.uji.ei102716cdg.dao;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.user.Student;

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
			student.setNick(rs.getString("nick"));
			student.setEmail(rs.getString("email"));
			student.setPasswd(rs.getString("passwd"));
			student.setName(rs.getString("name"));
			student.setDni(rs.getString("dni"));
			student.setAvatar(rs.getInt("avatar"));
			
			return student;
		}
	}
	
	/**Genera una lista con los estudiantes de la base de datos
	 * 
	 * La lista contiene, para cada estudiante, nick, email, contrase�a, nombre y apellidos, dni, 
	 * y la cantidad de puntos que tiene actualmente
	 * 
	 * @return Lista de estudiantes
	 * */
	public List<Student> getStudents() {
		return this.jdbcTemplate.query("select nick, email, passwd, name, dni, avatar from Student",
				new StudentMapper());
	}
	
	/**Busca en la base de datos el estudiante asociado al nombre de usuario dado (nick)
	 * 
	 * @param 	nick
	 * @return 	estudiante asociado al nick
	 */
	public Student getStudent(String nick) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM Student WHERE nick = ?",
				new Object[] {nick}, new StudentMapper());

	}
	
	/**Registra al estudiante dado en la base de datos
	 * 
	 * @param 	student: Estudiante a almacenar en el sistema	
	 */
	public void addStudent(Student student) {
		this.jdbcTemplate.update("insert into Student(nick, email, passwd, name, dni, avatar) "
				+ "values(?, ?, ?, ?, ?, ?)",
				student.getNick(), student.getEmail(), student.getPasswd(), student.getName(), student.getDni(), student.getAvatar());
	}

	/**Si existe un estudiante con el mismo nick en la base de datos lo sobreescribe
	 * 
	 * @param student: Estudiante con los nuevos datos que quieren almacenarse
	 */
	public void updateStudent(Student student) {
		this.jdbcTemplate.update("update student "
				+ "set nick = ?,"
				+ " email = ?,"
				+ " passwd = ?,"
				+ " name = ?,"
				+ " dni = ?,"
				+ " avatar = ?"
				+ " where nick = ?",
				student.getNick(), student.getEmail(), student.getPasswd(), student.getName(), student.getDni(), student.getAvatar(), student.getNick());
	}

	/**Borra de la base de datos al estudiante asociado a un nombre de usuario dado
	 * 
	 * @param nick: Nombre de usuario �nico del estudiante que se desea borrar
	 */
	public void deleteStudent(String nick) {
		this.jdbcTemplate.update("DELETE FROM Student WHERE nick = ?" , nick);
	}
}
