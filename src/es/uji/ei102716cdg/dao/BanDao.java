package es.uji.ei102716cdg.dao;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.user.Ban;

import java.util.List;

import java.sql.SQLException;
import java.sql.ResultSet;


@Repository
public class BanDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	private static final class BanMapper implements RowMapper<Ban>{
		public Ban mapRow(ResultSet rs, int rowNum) throws SQLException {
			Ban ban = new Ban();
			ban.setStudent_nick(rs.getString("student_nick"));
			ban.setBanDate(rs.getDate("banDate"));
			ban.setDays(rs.getInt("days"));
			ban.setReason(rs.getString("reason"));
			
			return ban;
		}
	}
	
	/**Genera una lista con los estudiantes baneados, registrados en la base de datos
	 * 
	 * La lista contiene, para cada estudiante benado: nick, fecha de inicio del baneo, dias desde el inicio del baneo y el motivo de este.
	 *  
	 * @return Lista de estudiantes baneados
	 * */
	public List<Ban> getBans() {
		return this.jdbcTemplate.query("select student_nick, banDate, days, reason from Ban",
				new BanMapper());
	}
	
	/**Busca en la base de datos el estudiante baneado asociado al nombre de usuario dado (nick)
	 * 
	 * @param 	student_nick: Nombre de usuario del estudiante buscado
	 * @return 	estudiante asociado al nick
	 */
	public Ban getBan(String studentNick) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM Ban WHERE student_nick = ?",
				new Object[] {studentNick}, new BanMapper());

	}
	
	/**Registra un nuevo baneo en la base de datos
	 * 
	 * @param 	ban: Datos del baneo	
	 */
	public void addBan(Ban ban) {
		this.jdbcTemplate.update("insert into Ban(student_nick, banDate, days, reason) "
				+ "values(?, ?, ?, ?)",
				ban.getStudent_nick(), ban.getBanDate(), ban.getDays(), ban.getReason());
	}

	/**Modifica un baneo existente
	 * 
	 * Si existe un baneo asociado al mismo nick sobreescribe los datos
	 * @param ban: Nuevos datos del baneo
	 */
	public void updateBan(Ban ban) {
		this.jdbcTemplate.update("update ban "
				+ "set student_nick = ?,"
				+ " banDate = ?,"
				+ " days = ?,"
				+ " reason = ?"
				+ " where student_nick = ?",
				ban.getStudent_nick(), ban.getBanDate(), ban.getDays(), ban.getReason(), ban.getStudent_nick());
	}

	/**Borra de la base de datos el baneo asociado al nombre de usuario dado
	 * 
	 * @param student_nick: Nombre de usuario asociado al beneo que se desea borrar
	 */
	public void deleteBan(String studentNick) {
		this.jdbcTemplate.update("DELETE FROM Ban WHERE student_nick = ?", studentNick);
	}
}
