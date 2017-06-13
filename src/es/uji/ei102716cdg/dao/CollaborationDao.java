package es.uji.ei102716cdg.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.collaboration.Collaboration;


@Repository
public class CollaborationDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	private static final class CollaborationMapper implements RowMapper<Collaboration>{
		public Collaboration mapRow(ResultSet rs, int rowNum) throws SQLException {
			Collaboration collaboration = new Collaboration();
			collaboration.setCollaboration_id(rs.getInt("collaboration_id"));
			collaboration.setOffer_id(rs.getInt("offer_id"));
			collaboration.setRequest_id(rs.getInt("request_id"));
			collaboration.setRating(rs.getShort("rating"));
			collaboration.setTotalHours(rs.getShort("totalHours"));
			collaboration.setComments(rs.getString("comments"));
			return collaboration;
		}
	}

	public List<Collaboration> getCollaborations() {
		return this.jdbcTemplate.query("select * from Collaboration",
				new CollaborationMapper());
	}
	
	public Collaboration getCollaboration(int collaboration_id) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM Collaboration WHERE collaboration_id = ?",
				new Object[] {collaboration_id}, new CollaborationMapper());
	}
	
	public void addCollaboration(Collaboration collaboration) {
		this.jdbcTemplate.update("insert into Collaboration( offer_id, request_id, rating, totalHours, comments )"
				+ "values(?, ?, ?, ?, ?)",
				collaboration.getOffer_id(), collaboration.getRequest_id(),collaboration.getRating(), collaboration.getTotalHours(), collaboration.getComments());
	}

	public void updateCollaboration(Collaboration collaboration) {
		this.jdbcTemplate.update("update collaboration "
				+ "set offer_id = ?,"
				+ " request_id = ?,"
				+ " rating = ?,"
				+ " totalHours = ?,"
				+ " comments = ?"
				+ " WHERE collaboration_id = ?",
				collaboration.getOffer_id(), collaboration.getRequest_id(), collaboration.getRating(), collaboration.getTotalHours(), collaboration.getComments(), collaboration.getCollaboration_id());
	}

	public void deleteCollaboration(int collaboration_id) {
		this.jdbcTemplate.update("DELETE FROM Collaboration WHERE collaboration_id = ?", collaboration_id);
	}
	
	public List<Collaboration> getAllCollaborationsByNick(String nick) {
		return this.jdbcTemplate.query("select * from Collaboration WHERE offer_id IN "
				+ "(SELECT offer_id FROM offer WHERE student_nick = ?)"
				+ "OR request_id IN (SELECT request_id FROM request WHERE student_nick = ?)",
				new Object[] {nick, nick}, new CollaborationMapper());
	}
	
	public List<Collaboration> getActiveCollaborationsByNick(String nick) {
		return this.jdbcTemplate.query("select * from Collaboration WHERE (offer_id IN "
				+ "(SELECT offer_id FROM offer WHERE student_nick = ?)"
				+ "OR request_id IN (SELECT request_id FROM request WHERE student_nick = ?))"
				+ " AND "
				+ "(offer_id IN (SELECT offer_id FROM offer WHERE endDate >= current_date )"
				+ "AND request_id IN (SELECT request_id FROM request WHERE endDate >= current_date ) )",
				new Object[] {nick, nick}, new CollaborationMapper());
	}
	
	public List<Collaboration> getOldCollaborationsByNick(String nick) {
		return this.jdbcTemplate.query("select * from Collaboration WHERE (offer_id IN "
				+ "(SELECT offer_id FROM offer WHERE student_nick = ?)"
				+ "OR request_id IN (SELECT request_id FROM request WHERE student_nick = ?))"
				+ " AND "
				+ "(offer_id IN (SELECT offer_id FROM offer WHERE endDate < current_date )"
				+ "OR request_id IN (SELECT request_id FROM request WHERE endDate < current_date ) )"
				+ "AND (totalHours != 0 AND rating != 0)",
				new Object[] {nick, nick}, new CollaborationMapper());
	}
	
	public List<Collaboration> getEvalCollaborationsByNick(String nick) {
		return this.jdbcTemplate.query("select * from Collaboration WHERE (offer_id IN "
				+ "(SELECT offer_id FROM offer WHERE student_nick = ?)"
				+ "OR request_id IN (SELECT request_id FROM request WHERE student_nick = ?))"
				+ " AND "
				+ "(offer_id IN (SELECT offer_id FROM offer WHERE endDate < current_date )"
				+ "OR request_id IN (SELECT request_id FROM request WHERE endDate < current_date ) )"
				+ "AND (totalHours = 0 AND rating = 0)",
				new Object[] {nick, nick}, new CollaborationMapper());
	}
	
	public int addCollaborationAndGetId(Collaboration collaboration){
		KeyHolder holder = new GeneratedKeyHolder();
		final Collaboration collab = collaboration;
		
		this.jdbcTemplate.update(
				new PreparedStatementCreator() {           

	                @Override
	                public PreparedStatement createPreparedStatement(Connection connection)
	                        throws SQLException {
	                	String sql = "insert into Collaboration( offer_id, request_id, rating, totalHours, comments ) "
	            				+ "values(?, ?, ?, ?, ?)";
	                    PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
	                    ps.setInt(1, collab.getOffer_id());
	                    ps.setInt(2, collab.getRequest_id());
	                    ps.setShort(3, collab.getRating());
	                    ps.setShort(4, collab.getTotalHours());
	                    ps.setString(5, collab.getComments());
	                    return ps;
	                }
	            }, holder);
		
		return (Integer)holder.getKeys().get("collaboration_id");
		
	}
}
