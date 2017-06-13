package es.uji.ei102716cdg.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.collaboration.Offer;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


@Repository
public class OfferDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	private static final class OfferMapper implements RowMapper<Offer>{
		public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Offer offer = new Offer();
			offer.setId(rs.getInt("offer_id"));
			offer.setStudent_nick(rs.getString("student_nick"));
			offer.setSkill_Id(rs.getInt("skill_id"));
			offer.setStartDate(rs.getDate("startDate"));			
			offer.setEndDate(rs.getDate("endDate"));	
			offer.setDescription(rs.getString("description"));
			offer.setActive(rs.getBoolean("active"));			
			return offer;
		}
	}
	
	/**Genera una lista con las ofertas de la base de datos
	 * 
	 * La lista contiene, para cada oferta: su id, el nick del estudiante que la ha piblicado, la id de la skill a la que hace referencia
	 * la fecha en la que empieza, la fecha en la que acaba, una breve descripci�n de la oferta, y si esta activa o inactiva.
	 * 
	 * @return Lista de ofertas
	 * */
	public List<Offer> getOffers() {
		return this.jdbcTemplate.query("select offer_id, student_nick, skill_id, startDate, endDate, description, active from Offer",
				new OfferMapper() );
	}
	
	public List<Offer> getRecentOffers() {
		return this.jdbcTemplate.query("select offer_id, student_nick, skill_id, startDate, endDate, description, active from Offer"
										+ " ORDER BY startDate DESC LIMIT 6",
				new OfferMapper() );
	}
	
	/**Busca en la base de datos la oferta asociada a una id
	 * 
	 * @param 	id
	 * @return 	oferta asociada a la id
	 */
	public Offer getOffer(int id) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM Offer WHERE offer_id = ?",
				new Object[] {id}, new OfferMapper());
	}
	
	/**Registra la oferta dada en la base de datos
	 * 
	 * @param 	offer: Oferta a almacenar en el sistema	
	 */
	public void addOffer(Offer offer) {
		this.jdbcTemplate.update("insert into Offer(student_nick, skill_id, startDate, endDate, description, active) "
				+ "values(?, ?, ?, ?, ?, ?)",
				offer.getStudent_nick(), offer.getSkill_Id(), offer.getStartDate(), offer.getEndDate(), offer.getDescription(), offer.isActive());
	}

	/**Actualiza los datos de una oferta
	 * 
	 * Si existe una oferta con la misma id la sobreescribe
	 * 
	 * @param offer: Oferta con los nuevos datos que quieren almacenarse
	 */
	public void updateOffer(Offer offer) {
		this.jdbcTemplate.update("update Offer "
				+ "set offer_id = ?,"
				+ "student_nick = ?,"
				+ "skill_Id = ?,"
				+ "startDate = ?,"
				+ "endDate = ?,"
				+ "description = ?,"
				+ " active = ?"
				+ " where offer_id = ?",
				offer.getId(), offer.getStudent_nick(), offer.getSkill_Id(), offer.getStartDate(), offer.getEndDate(), offer.getDescription(), offer.isActive(), offer.getId() );
	}

	/**Borra de la base de datos la oferta asociada a una id
	 * 
	 * @param id: Id identificadora de la oferta
	 */
	public void deleteOffer(int id) {
		this.jdbcTemplate.update("DELETE FROM Offer WHERE offer_id = ?", id);
	}
	
	/** Obtener las ofertas publicadas por un usuario
	 * 
	 *  @param nick: nombre de usuario del que se quiere consultar
	 *  @return lista de ofertas
	 */
	
	public List<Offer> getOffersByNick(String student_nick){
		return this.jdbcTemplate.query("SELECT * FROM Offer WHERE student_nick = ?",
				new Object[] {student_nick}, new OfferMapper());
	}
	
	public int addOfferAndGetId(Offer offer) {
		
		KeyHolder holder = new GeneratedKeyHolder();
		
		final Offer off = offer;
		
		this.jdbcTemplate.update(
				new PreparedStatementCreator() {           

	                @Override
	                public PreparedStatement createPreparedStatement(Connection connection)
	                        throws SQLException {
	                	String sql = "insert into Offer(student_nick, skill_id, startDate, endDate, description, active) "
	            				+ "values(?, ?, ?, ?, ?, ?)";
	                    PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
	                    ps.setString(1, off.getStudent_nick());
	                    ps.setInt(2, off.getSkill_Id());
	                    ps.setDate(3, off.getStartDate());
	                    ps.setDate(4, off.getEndDate());
	                    ps.setString(5, off.getDescription());
	                    ps.setBoolean(6, off.isActive());
	                    return ps;
	                }
	            }, holder);
		
		return (Integer)holder.getKeys().get("offer_id");
	}
	
	public List<Offer> searchOffers(String query, String nick){
		query = query.replaceAll("[^A-Za-z0-9]", "");
		if (query.trim().equals("")) return new ArrayList<Offer>();
		query += ":*";
		return this.jdbcTemplate.query("select off.* from offer AS off "
				+ "JOIN skill as sk ON (off.skill_Id = sk.skill_Id) "
		+ "where ( "
		+ "(to_tsvector(off.description) @@ (to_tsquery(?))) "
		+ "OR (to_tsvector(sk.name) @@ (to_tsquery(?))) "
		+ "OR ((to_tsvector(sk.description)) @@ (to_tsquery(?))) "
		+ ") AND ("
		+ "off.active = true AND off.endDate >= CURRENT_DATE AND off.student_nick <> ?"
		+ ")",
		new Object[] {query, query, query, nick}, new OfferMapper() );
	}

}
