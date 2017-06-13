package es.uji.ei102716cdg.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.collaboration.Request;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


@Repository
public class RequestDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	private static final class RequestMapper implements RowMapper<Request>{
		public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
			Request request = new Request();
			request.setId(rs.getInt("request_id"));
			request.setStudent_nick(rs.getString("student_nick"));
			request.setSkill_Id(rs.getInt("skill_id"));
			request.setStartDate(rs.getDate("startDate"));			
			request.setEndDate(rs.getDate("endDate"));	
			request.setDescription(rs.getString("description"));
			request.setActive(rs.getBoolean("active"));			
			return request;
		}
	}
	
	/**Genera una lista con las ofertas de la base de datos
	 * 
	 * La lista contiene, para cada oferta: su id, el nick del estudiante que la ha piblicado, la id de la skill a la que hace referencia
	 * la fecha en la que empieza, la fecha en la que acaba, una breve descripci�n de la oferta, y si esta activa o inactiva.
	 * 
	 * @return Lista de ofertas
	 * */
	public List<Request> getRequests() {
		return this.jdbcTemplate.query("select request_id, student_nick, skill_id, startDate, endDate, description, active from Request",
				new RequestMapper() );
	}
	
	/**Busca en la base de datos la oferta asociada a una id
	 * 
	 * @param 	id
	 * @return 	oferta asociada a la id
	 */
	public Request getRequest(int id) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM Request WHERE request_id = ?",
				new Object[] {id}, new RequestMapper());
	}
	
	/**Registra la oferta dada en la base de datos
	 * 
	 * @param 	request: Oferta a almacenar en el sistema	
	 */
	public void addRequest(Request request) {
		this.jdbcTemplate.update("insert into Request(student_nick, skill_id, startDate, endDate, description, active) "
				+ "values(?, ?, ?, ?, ?, ?)",
				request.getStudent_nick(), request.getSkill_Id(), request.getStartDate(), request.getEndDate(), request.getDescription(), request.isActive());
	}

	/**Actualiza los datos de una oferta
	 * 
	 * Si existe una oferta con la misma id la sobreescribe
	 * 
	 * @param request: Oferta con los nuevos datos que quieren almacenarse
	 */
	public void updateRequest(Request request) {
		this.jdbcTemplate.update("update Request "
				+ "set request_id = ?,"
				+ "student_nick = ?,"
				+ "skill_Id = ?,"
				+ "startDate = ?,"
				+ "endDate = ?,"
				+ "description = ?,"
				+ " active = ?"
				+ " where request_id = ?",
				request.getId(), request.getStudent_nick(), request.getSkill_Id(), request.getStartDate(), request.getEndDate(), request.getDescription(), request.isActive(), request.getId() );
	}

	/**Borra de la base de datos la oferta asociada a una id
	 * 
	 * @param id: Id identificadora de la oferta
	 */
	public void deleteRequest(int id) {
		this.jdbcTemplate.update("DELETE FROM Request WHERE request_id = ?", id);
	}
	
	/** Obtener las demandas publicadas por un usuario
	 * 
	 *  @param nick: nombre de usuario del que se quiere consultar
	 *  @return lista de demandas
	 */
	
	public List<Request> getRequestsByNick(String student_nick){
		return this.jdbcTemplate.query("SELECT * FROM Request WHERE student_nick = ?",
				new Object[] {student_nick}, new RequestMapper());

	}
	
	public int addRequestAndGetId(Request request) {
		
		KeyHolder holder = new GeneratedKeyHolder();
		
		final Request req = request;
		
		this.jdbcTemplate.update(
				new PreparedStatementCreator() {           

	                @Override
	                public PreparedStatement createPreparedStatement(Connection connection)
	                        throws SQLException {
	                	String sql = "insert into Request(student_nick, skill_id, startDate, endDate, description, active) "
	            				+ "values(?, ?, ?, ?, ?, ?)";
	                    PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
	                    ps.setString(1, req.getStudent_nick());
	                    ps.setInt(2, req.getSkill_Id());
	                    ps.setDate(3, req.getStartDate());
	                    ps.setDate(4, req.getEndDate());
	                    ps.setString(5, req.getDescription());
	                    ps.setBoolean(6, req.isActive());
	                    return ps;
	                }
	            }, holder);
		
		return (Integer)holder.getKeys().get("request_id");
	}
	
	public List<Request> searchRequests(String query,String nick){
		query = query.replaceAll("[^A-Za-z0-9áéíóúñçàìòùè]", "");
		if (query.trim().equals("")) return new ArrayList<Request>();
		query += ":*";
		return this.jdbcTemplate.query("select req.* from request AS req "
				+ "JOIN skill as sk ON (req.skill_Id = sk.skill_Id) "
		+ "where ( "
		+ "(to_tsvector(req.description) @@ (to_tsquery(?))) "
		+ "OR (to_tsvector(sk.name) @@ (to_tsquery(?))) "
		+ "OR ((to_tsvector(sk.description)) @@ (to_tsquery(?))) "
		+ ") AND ("
		+ "req.active = true AND req.endDate >= CURRENT_DATE AND req.student_nick <> ?"
		+ ") ",
		new Object[] {query, query, query, nick}, new RequestMapper() );
	}

}
