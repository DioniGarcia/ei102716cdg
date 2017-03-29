package es.uji.ei102716cdg.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import es.uji.ei102716cdg.domain.Message;

public class MessageDao {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private static final class MessageMapper implements RowMapper<Message>{
		public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
			Message message = new Message();
			message.setId(rs.getInt("id"));
			message.setMsg(rs.getString("msg"));
			message.setStatus(rs.getShort("status"));
		
			return message;
		}
	}
	
	public List<Message> getMessages() {
		return this.jdbcTemplate.query("select * from Message",
				new MessageMapper());
	}
	
	public Message getMessage(int id) { 
		return this.jdbcTemplate.queryForObject("SELECT * FROM Message WHERE id = ?",
				new Object[] {id}, new MessageMapper());
	}
	
	public void addMessage(Message message) {
		this.jdbcTemplate.update("insert into Message(msg, status) "
				+ "values(?, ?)",
				message.getMsg(), message.getStatus()); 
	}

	public void updateMessage(Message message) {
		this.jdbcTemplate.update("update Message "
				+ "set msg = ?,"
				+ "status = ?,"
				+ " WHERE id = ?",
				message.getMsg(), message.getStatus(), message.getId()); 
	}

	public void deleteMessage(int id) {
		this.jdbcTemplate.update("DELETE FROM Message WHERE id = ?", id);
	}
}
