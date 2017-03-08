package es.uji.ei102716cdg.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.Request;

import java.util.List;

import java.sql.SQLException;
import java.sql.ResultSet;


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
			request.setId(rs.getInt("id"));
			request.setStartDate(rs.getDate("startDate"));
			request.setEndDate(rs.getDate("endDate"));
			request.setDescription(rs.getString("description"));
			request.setDescription(rs.getString("nif"));
			request.setSkill_id(rs.getInt("skillId"));
			
			return request;
		}
	}

	public List<Request> getRequests() {
		return this.jdbcTemplate.query("select * from request",
				new RequestMapper());
	}
	
	public Request getRequest(int id) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM Request WHERE id = ?",
				new Object[] {id}, new RequestMapper());
	}
	
	public void addRequest(Request request) {
		this.jdbcTemplate.update("insert into Request(id, startDate, endDate, "
				+ "description, nif, skillId) "
				+ "values(?, ?, ?, ?, ?, ?)",
				request.getId(), request.getStartDate(), request.getEndDate() , request.getDescription(), 
				request.getNif(), request.getSkillId());
	}

	public void updateRequest(Request request) {
		this.jdbcTemplate.update("update request "
				+ "set id = ?,"
				+ "startDate = ?,"
				+ "endDate = ?,"
				+ "description = ?"
				+ "nif = ?"
				+ "skillId = ?"
				+ "WEHRE id = ?",
				request.getId(), request.getStartDate(), request.getEndDate() , request.getDescription(), 
				request.getNif(), request.getSkillId());
	}

	public void deleteRequest(String id) {
		this.jdbcTemplate.update("DELETE FROM Request WHERE id = ?", id);
	}
}