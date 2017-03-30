package es.uji.ei102716cdg.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.Offer;

import java.util.List;

import java.sql.SQLException;
import java.sql.ResultSet;


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
			offer.setId(rs.getInt("id"));
			offer.setStartDate(rs.getDate("startDate"));
			offer.setEndDate(rs.getDate("endDate"));
			offer.setDescription(rs.getString("description"));
			offer.setNif(rs.getString("student_nif"));
			offer.setSkillId(rs.getInt("skill_id"));
			
			return offer;
		}
	}
	
	public List<String> getSkillsId() {
        return (List<String>) this.jdbcTemplate.queryForList( "select id from Skill", 
                                           String.class);
    }
	
	public List<String> getNifsId() {
        return (List<String>) this.jdbcTemplate.queryForList( "select nif from Student", 
                                           String.class);
    }
	
	public List<Offer> getOffers() {
		return this.jdbcTemplate.query("select * from Offer",
				new OfferMapper());
	}
	
	public Offer getOffer(int id) { 
		return this.jdbcTemplate.queryForObject("SELECT * FROM Offer WHERE id = ?",
				new Object[] {id}, new OfferMapper());
	}
	
	public void addOffer(Offer offer) {
		this.jdbcTemplate.update("insert into Offer( startDate, endDate, "
				+ "description, student_nif, skill_id) "
				+ "values( ?, ?, ?, ?, ?)",
				offer.getStartDate(), offer.getEndDate() , offer.getDescription(), 
				offer.getNif(), offer.getSkillId());
	}

	public void updateOffer(Offer offer) {
		this.jdbcTemplate.update("update Offer "
				+ "set startDate = ?,"
				+ "endDate = ?,"
				+ "description = ?,"
				+ "student_nif = ?,"
				+ "skill_id = ?"
				+ " WHERE id = ?",
				offer.getStartDate(), offer.getEndDate() , offer.getDescription(), 
				offer.getNif(), offer.getSkillId(), offer.getId());
	}

	public void deleteOffer(int id) {
		this.jdbcTemplate.update("DELETE FROM Offer WHERE id = ?", id);
	}
}
