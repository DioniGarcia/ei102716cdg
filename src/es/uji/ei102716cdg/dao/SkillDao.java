package es.uji.ei102716cdg.dao;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.Skill;

import java.util.List;

import java.sql.SQLException;
import java.sql.ResultSet;


@Repository
public class SkillDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	private static final class SkillMapper implements RowMapper<Skill>{
		public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
			Skill skill = new Skill();
			skill.setId(rs.getInt("id"));
			skill.setDescription(rs.getString("name"));
			skill.setDescription(rs.getString("description"));
			skill.setDescription(rs.getString("level"));
			skill.setStatus(rs.getBoolean("status"));
		
			return skill;
		}
	}

	public List<Skill> getSkills() {
		return this.jdbcTemplate.query("select * from Skill",
				new SkillMapper());
	}
	
	public Skill getSkill(int id) { 
		return this.jdbcTemplate.queryForObject("SELECT * FROM Skill WHERE id = ?",
				new Object[] {id}, new SkillMapper());
	}
	
	public void addSkill(Skill skill) {
		this.jdbcTemplate.update("insert into Skill(id, namee, description, level, status) "
				+ "values(?, ?, ?, ?, ?, ?)",
				skill.getId(), skill.getName(), skill.getDescription(), 
				skill.getLevel(), skill.getStatus());
	}

	public void updateSkill(Skill skill) {
		this.jdbcTemplate.update("update Skill "
				+ "set id = ?,"
				+ "name = ?,"
				+ "description = ?"
				+ "level = ?"
				+ "status = ?"
				+ "WEHRE id = ?",
				skill.getId(), skill.getName(), skill.getDescription(), 
				skill.getLevel(), skill.getStatus(), skill.getId());
	}

	public void deleteSkill(String id) {
		this.jdbcTemplate.update("DELETE FROM Skill WHERE id = ?", id);
	}
}
