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
import es.uji.ei102716cdg.domain.skill.SkillWithStats;


@Repository
public class SkillDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final class SearchMapper implements RowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return rs.getString("name");
		}
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
	
	private static final class SkillWithStatsMapper implements RowMapper<SkillWithStats>{
		public SkillWithStats mapRow(ResultSet rs, int rowNum) throws SQLException {
			SkillWithStats skill = new SkillWithStats();
			skill.setSkill_id(rs.getInt("skill_id"));
			skill.setName(rs.getString("name"));
			skill.setDescription(rs.getString("description"));
			skill.setLevel(rs.getString("level"));
			skill.setActive(rs.getBoolean("active"));
			skill.setNumberOffers(rs.getInt("off"));
			skill.setNumberRequests(rs.getInt("req"));
			return skill;
		}
	}
	
	/**Genera una lista con los tipos de habilidad (skill) de la base de datos
	 * 
	 * La lista contiene, para cada skill: su id, nombre, descripci�n, nivel y su estado (activo o no, en el sistema) 
	 * 
	 * @return Lista de skills
	 * */
	public List<Skill> getSkills() {
		return this.jdbcTemplate.query("select skill_id, name, description, level, active from Skill order by skill_id",
				new SkillMapper());
	}
	
	/**Genera una lista con los tipos de habilidad (skill) de la base de datos más el numero  de ofertas y demandas
	 * 
	 * La lista contiene, para cada skill: su id, nombre, descripción, nivel y su estado (activo o no, en el sistema) 
	 * 
	 * @return Lista de skills con stats
	 * */
	public List<SkillWithStats> getSkillsWithStats() {
		return this.jdbcTemplate.query("select skill_id, name, description, level, active, (select count(*) from offer where skill_id = o.skill_id) as off, (select count(*) from request where skill_id = o.skill_id) as req from Skill o order by skill_id",
				new SkillWithStatsMapper());
	}
	
	/**Busca en la base de datos todos los niveles de una skill asociada a un nombre 
	 * 
	 * @param 	name
	 * @return 	lista con una skill para cada uno de los niveles
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
	
	/*
	 * Actualiza el atributo de la habilidad en la base de datos.
	 */
	public int setActive(int id, boolean active){
		return this.jdbcTemplate.update("update Skill set active = ? where skill_id = ?", active, id);
	}
	

	public int setName(String original, String name){
		original += ":*";
		return this.jdbcTemplate.update("update Skill set name = ? where (to_tsvector(name) @@ (plainto_tsquery(?)))", name, original);
	}
	
	public int setDescription(int id, String description){
		return this.jdbcTemplate.update("update Skill set description = ? where skill_id = ?", description, id);
	}
	
	//TODO Convertir en un servicio, a ser posible
	/**Busca todas las skills que contengan en su nombre la cadena dada
	 * 
	 * @param 	nameSubstring: Parte del nombre que se está buscando
	 * @return	Lista de skills que contienen nameSubstring en su nombre
	 */
	public List<String> searchSkill(String nameSubstring) {
		nameSubstring += ":*";
		return this.jdbcTemplate.query("select DISTINCT name FROM Skill "
				+ " WHERE (to_tsvector(name) @@ (to_tsquery(?))) AND active = true",
				new Object[] {nameSubstring}, new SearchMapper());
	} 
	
	public List<String> skillNames() {
		return this.jdbcTemplate.query("select DISTINCT name FROM Skill "
				+ " WHERE active = true",
				new Object[] {}, new SearchMapper());
	} 
	
	//TODO Convertir en un servicio, a ser posible
	/**Devuelve los niveles activos de la skill deseada	
	 * 
	 * @param 	name: Nombre de la skill deseada
	 * @return 	Lista de skills para los niveles activos
	 */
	public List<Skill> skillLevels(String name) {
		return this.jdbcTemplate.query("select * FROM Skill "
				+ " WHERE name=? AND active = true ORDER BY skill_id",
				new Object[] {name}, new SkillMapper());
	}
	
	
}
