package es.uji.ei102716cdg.dao;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.Collaboration;

import java.util.List;

import java.sql.SQLException;
import java.sql.ResultSet;


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
			collaboration.setId(rs.getInt("id"));
			collaboration.setStartDate(rs.getDate("startDate"));
			collaboration.setEndDate(rs.getDate("endDate"));
			collaboration.setTotalHours(rs.getShort("totalHours"));
			collaboration.setComments(rs.getString("comments"));
			collaboration.setRating(rs.getShort("rating"));
			collaboration.setOfferId(rs.getInt("offerId"));
			collaboration.setRequestId(rs.getInt("requestId"));
			return collaboration;
		}
	}

	public List<Collaboration> getCollaborations() {
		return this.jdbcTemplate.query("select * from Collaboration",
				new CollaborationMapper());
	}
	
	public Collaboration getCollaboration(int id) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM Collaboration WHERE id = ?",
				new Object[] {id}, new CollaborationMapper());
	}
	
	public void addCollaboration(Collaboration collaboration) {
		this.jdbcTemplate.update("insert into Collaboration(id, startDate, endDate, "
				+ "totalHours, comments, rating, offerId,requestId) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?)",
				collaboration.getId(), collaboration.getStartDate(), collaboration.getEndDate() , collaboration.getTotalHours(), 
				collaboration.getComments(), collaboration.getRating(), collaboration.getOfferId(),collaboration.getRequestId());
	}

	public void updateStudent(Collaboration collaboration) {
		this.jdbcTemplate.update("update collaboration "
				+ "set id = ?,"
				+ "startDate = ?,"
				+ "endDate = ?,"
				+ "totalHours = ?"
				+ "comments = ?"
				+ "rating = ?"
				+ "offerId = ?"
				+ "requestId = ?"
				+ "WHERE id = ?",
				collaboration.getId(), collaboration.getStartDate(), collaboration.getEndDate() , 
				collaboration.getTotalHours(),collaboration.getComments(), collaboration.getRating(), 
				collaboration.getOfferId(),collaboration.getRequestId(), collaboration.getId());
	}

	public void deleteCollaboration(String id) {
		this.jdbcTemplate.update("DELETE FROM Collaboration WHERE id = ?", id);
	}
}
