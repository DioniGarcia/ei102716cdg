package es.uji.ei102716cdg.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.skill.Skill;


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
			skill.setSkill_id(rs.getInt("skill_id"));
			skill.setName(rs.getString("name"));
			skill.setDescription(rs.getString("description"));
			skill.setLevel(rs.getString("level"));
			skill.setActive(rs.getBoolean("active"));
			
			return skill;
		}
	}
	
	/**Genera una lista con los tipos de habilidad (skill) de la base de datos
	 * 
	 * La lista contiene, para cada skill: su id, nombre, descripción, nivel y su estado (activo o no, en el sistema) 
	 * 
	 * @return Lista de skills
	 * */
	public List<Skill> getSkills() {
		return this.jdbcTemplate.query("select skill_id, name, description, level, active from Skill order by skill_id",
				new SkillMapper());
	}
	
	/**Busca en la base de datos todos los niveles de una skill asociada a un nombre 
	 * 
	 * @param 	name
	 * @return 	lista con una skill en todos los niveles
	 */
	public List<Skill> getSkillAllLevels(String name) {
		return this.jdbcTemplate.query("select skill_id, name, description, level, active from Skill WHERE name = ?",
				new Object[] {name}, new SkillMapper());
	}
	
	/**Busca en la base de datos un tipo de habilidad (skill) asociada a una id 
	 * 
	 * @param 	skill_id
	 * @return 	skill asociada a la id
	 */
	public Skill getSkill(int skill_id) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM Skill WHERE skill_id = ?",
				new Object[] {skill_id}, new SkillMapper());
	}
	
	/**Registra la skill dada en la base de datos
	 * 
	 * @param 	skill: Skill a almacenar en el sistema	
	 */
	public void addSkill(Skill skill) {
		this.jdbcTemplate.update("insert into Skill(name, description, level, active) "
				+ "values(?, ?, ?, ?)",
				skill.getName(), skill.getDescription(), skill.getLevel(), skill.isActive());
	}

	/**Actualiza los datos de un tipo de habilidad en un nivel concreto (skill)
	 * 
	 * Si existe un tipo de habilidad (skill) con la misma id lo sobreescribe
	 * 
	 * @param skill: Skill con los nuevos datos que quieren almacenarse
	 */
	public void updateSkill(Skill skill) {
		this.jdbcTemplate.update("update Skill "
				+ "set skill_id = ?,"
				+ " name = ?,"
				+ " description = ?,"
				+ " level = ?,"
				+ " active = ?"
				+ " where skill_id = ?",
				skill.getSkill_id(), skill.getName(), skill.getDescription(), skill.getLevel(), skill.isActive(), skill.getSkill_id() ); 
	}

	/**Borra de la base de datos la skill asociada a una id (un unico nivel)
	 * 
	 * @param skill_id: Id unica de la skill
	 */
	public void deleteSkill(int skill_id) {
		this.jdbcTemplate.update("DELETE FROM Skill WHERE skill_id = ?", skill_id);
	}
	
}
