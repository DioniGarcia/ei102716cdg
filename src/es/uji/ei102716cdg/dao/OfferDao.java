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
			offer.setDescription(rs.getString("nif"));
			offer.setSkill_id(rs.getInt("skillId"));
			
			return offer;
		}
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
		this.jdbcTemplate.update("insert into Offer(id, startDate, endDate, "
				+ "description, nif, skillId) "
				+ "values(?, ?, ?, ?, ?, ?)",
				offer.getId(), offer.getStartDate(), offer.getEndDate() , offer.getDescription(), 
				offer.getNif(), offer.getSkillId());
	}

	public void updateOffer(Offer offer) {
		this.jdbcTemplate.update("update Offer "
				+ "set id = ?,"
				+ "startDate = ?,"
				+ "endDate = ?,"
				+ "description = ?"
				+ "nif = ?"
				+ "skillId = ?"
				+ "WEHRE id = ?",
				offer.getId(), offer.getStartDate(), offer.getEndDate() , offer.getDescription(), 
				offer.getNif(), offer.getSkillId(), offer.getId());
	}

	public void deleteOffer(String id) {
		this.jdbcTemplate.update("DELETE FROM Offer WHERE id = ?", id);
	}
}
