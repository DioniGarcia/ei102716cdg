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
public class MessageDao {

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
	
	/**Genera una lista con los mensajes almacenados en la base de datos
	 * 
	 * La lista contiene, para cada mensaje: su id, la id del chat al que pertenece, el nick del estudiante
	 * que envi� el mensaje, el contenido del mensaje, la fecha en que se envi� y si ha sido o no leido.
	 *  
	 * @return Lista de mensajes
	 * */
	public List<Message> getMessages() {
		return this.jdbcTemplate.query("select message_id, chat_id, sender_id, content, sendingDate, active from Message ORDER BY sendingDate",
				new MessageMapper());
	}
	
	/**Busca en la base de datos el message asociado a una id dada
	 * 
	 * @param 	message_id: Identificador �nico del message
	 * @return 	mensaje asociado a la id
	 */
	public Message getMessage(int message_id) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM Message WHERE message_id = ?",
				new Object[] {message_id}, new MessageMapper());

	}
	
	/**Registra un nuevo mensaje en la base de datos
	 * 
	 * @param 	message: Datos del mensaje
	 */
	public void addMessage(Message message) {
		this.jdbcTemplate.update("insert into Message(chat_id, sender_id, content, sendingDate, active) "
				+ "values(?, ?, ?, NOW(), ?)",
				message.getChatId(), message.getSenderNick(), message.getContent(), message.isActive() );
	}

	/**Modifica un mensaje existente
	 * 
	 * Si existe un mensaje asociado a la misma id sobreescribe los datos
	 * @param message: Nuevos datos del mensaje
	 */
	public void updateMessage(Message message) {
		this.jdbcTemplate.update("update message "
				+ "set chat_id = ?,"
				+ " sender_id = ?,"
				+ " content = ?,"
				+ " sendingDate = ?,"
				+ " active = ?"
				+ " where message_id = ?",
				message.getChatId(), message.getSenderNick(), message.getContent(), message.getSendingDate(), message.isActive(), message.getMessageId() );
	}

	/**Borra de la base de datos el mensaje asociado a la id dada
	 * 
	 * @param message_id: Id asociada al mensaje que se desea borrar
	 */
	public void deleteMessage(int message_id) {
		this.jdbcTemplate.update("DELETE FROM Message WHERE message_id = ?", message_id);
	}
}
