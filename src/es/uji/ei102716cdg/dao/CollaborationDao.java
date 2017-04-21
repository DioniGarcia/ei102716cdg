package es.uji.ei102716cdg.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
}
