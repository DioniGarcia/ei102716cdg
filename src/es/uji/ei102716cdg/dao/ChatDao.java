package es.uji.ei102716cdg.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import es.uji.ei102716cdg.domain.Chat;

public class ChatDao {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private static final class ChatMapper implements RowMapper<Chat>{
		public Chat mapRow(ResultSet rs, int rowNum) throws SQLException {
			Chat chat = new Chat();
			chat.setId(rs.getInt("id"));
			chat.setSenderNif(rs.getString("sender_nif"));
			chat.setReceiverNif(rs.getString("receiver_nif"));
			chat.setCreatedAt(rs.getTimestamp("created_at"));
			chat.setMessageId(rs.getInt("message_id"));
		
			return chat;
		}
	}
	
	public List<Chat> getChats() {
		return this.jdbcTemplate.query("select * from Chat",
				new ChatMapper());
	}
	
	public Chat getChat(int id) { 
		return this.jdbcTemplate.queryForObject("SELECT * FROM Chat WHERE id = ?",
				new Object[] {id}, new ChatMapper());
	}
	
	public void addChat(Chat chat) {
		this.jdbcTemplate.update("insert into Chat(sender_nif, receiver_nif, created_at, message_id) "
				+ "values(?, ?, ?, ?)",
				chat.getSenderNif(), chat.getReceiverNif(), chat.getCreatedAt(), chat.getMessageId());
	}

	public void updateChat(Chat chat) {
		this.jdbcTemplate.update("update Chat "
				+ "set sender_nif = ?,"
				+ "receiver_nif = ?,"
				+ "created_at = ?,"
				+ "message_id = ?"
				+ " WHERE id = ?",
				chat.getSenderNif(), chat.getReceiverNif(), chat.getCreatedAt(), chat.getMessageId(), chat.getId());
	}

	public void deleteChat(int id) {
		this.jdbcTemplate.update("DELETE FROM Chat WHERE id = ?", id);
	}
}
