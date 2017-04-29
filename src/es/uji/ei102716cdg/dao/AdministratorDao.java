package es.uji.ei102716cdg.dao;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.user.Administrator;

import java.util.List;

import java.sql.SQLException;
import java.sql.ResultSet;


@Repository
public class AdministratorDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	private static final class AdministratorMapper implements RowMapper<Administrator>{
		public Administrator mapRow(ResultSet rs, int rowNum) throws SQLException {
			Administrator administrator = new Administrator();
			administrator.setNick( rs.getString("nick") );
			administrator.setEmail( rs.getString("email") );
			administrator.setPasswd( rs.getString("passwd") );
			
			return administrator;
		}
	}
	
	/**Genera una lista con los administradores de la base de datos
	 * 
	 * La lista contiene, para cada administrador, nick, email, y contraseña
	 * 
	 * @return Lista de administradores
	 * */
	public List<Administrator> getAdministrators() {
		return this.jdbcTemplate.query("select nick, email, passwd from Administrator",
				new AdministratorMapper());
	}
	
	/**Busca en la base de datos el administrador asociado al nombre de usuario dado (nick)
	 * 
	 * @param 	nick
	 * @return 	administrador asociado al nick
	 */
	public Administrator getAdministrator(String nick) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM Administrator WHERE nick = ?",
				new Object[] {nick}, new AdministratorMapper());

	}
	
	/**Registra al administrador dado en la base de datos
	 * 
	 * @param 	administrator: Administrador a almacenar en el sistema	
	 */
	public void addAdministrator(Administrator administrator) {
		this.jdbcTemplate.update("insert into Administrator(nick, email, passwd) "
				+ "values(?, ?, ?)",
				administrator.getNick(), administrator.getEmail(), administrator.getPasswd());
	}

	/**Actualiza el administrador asociado al nick dado
	 * 
	 * Si existe un administrador con el mismo nick en la base de datos lo sobreescribe,
	 * 
	 * @param administrator: Administrador con los nuevos datos que quieren almacenarse
	 */
	public void updateAdministrator(Administrator administrator) {
		this.jdbcTemplate.update("update administrator "
				+ "set nick = ?,"
				+ " email = ?,"
				+ " passwd = ?"
				+ " where nick = ?",
				administrator.getNick(), administrator.getEmail(), administrator.getPasswd(), administrator.getNick());
	}

	/**Borra de la base de datos al administrador asociado a un nombre de usuario dado
	 * 
	 * @param nick: Nombre de usuario único del administrador que se desea borrar
	 */
	public void deleteAdministrator(String nick) {
		this.jdbcTemplate.update("DELETE FROM Administrator WHERE nick = ?", nick);
	}
}
