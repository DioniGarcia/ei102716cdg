package es.uji.ei102716cdg.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.chat.Message;

@Repository
public class UnreadMessageDao extends MessageDao {
	
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	private static final class MessageMapper implements RowMapper<Message>{
		public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
			Message message = new Message();
			message.setMessageId(rs.getInt("message_id"));
			message.setChatId(rs.getInt("chat_id"));
			message.setSenderNick(rs.getString("sender_id"));
			message.setContent(rs.getString("content"));
			message.setSendingDate(rs.getTimestamp("sendingDate"));
			message.setActive(rs.getBoolean("active"));
			return message;
		}
	}
	
	
	public List<Message> getUnreadMessages(String nick) {
		return this.jdbcTemplate.query("select message_id, chat_id, sender_id, content, sendingDate, active from Message WHERE chat_id IN "
				+ " ( SELECT chat_id FROM Chat WHERE user_one = ? OR user_two = ? ) AND sender_id <> ? AND active = false",
				new Object[] {nick, nick, nick}, new MessageMapper());
	}
	
	public void setReadMessages(String nick, int chatId){
		this.jdbcTemplate.update("update Message set active = true WHERE chat_id = ? AND sender_id <> ? AND active = false",
				chatId, nick);
	}
	
}
