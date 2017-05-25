package es.uji.ei102716cdg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import es.uji.ei102716cdg.domain.chat.Chat;

@Repository
public class ChatDao {

private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	private static final class ChatMapper implements RowMapper<Chat>{
		public Chat mapRow(ResultSet rs, int rowNum) throws SQLException {
			Chat chat = new Chat();
			chat.setChatId(rs.getInt("chat_id"));
			chat.setNickUserOne(rs.getString("user_one"));
			chat.setNickUserTwo(rs.getString("user_two"));
			return chat;
		}
	}
	
	/**Genera una lista con los chats almacenados en la base de datos
	 * 
	 * La lista contiene, para cada chat: su id, y el nick de los dos estudiantes participantes
	 *  
	 * @return Lista de chats
	 * */
	public List<Chat> getChats() {
		return this.jdbcTemplate.query("select chat_id, user_one, user_two from Chat",
				new ChatMapper());
	}
	
	/**Busca en la base de datos el chat asociado a una id dada
	 * 
	 * @param 	chat_id: Identificador �nico del chat
	 * @return 	chat asociado a la id
	 */
	public Chat getChat(int chat_id) {
		return this.jdbcTemplate.queryForObject("SELECT * FROM Chat WHERE chat_id = ?",
				new Object[] {chat_id}, new ChatMapper());

	}
	
	/**Registra un nuevo chat en la base de datos
	 * 
	 * @param 	chat: Datos del chat
	 */
	public void addChat(Chat chat) {
		this.jdbcTemplate.update("insert into Chat( user_one, user_two) "
				+ "values(?, ?)",
				chat.getNickUserOne(), chat.getNickUserTwo() );
	}

	/**Modifica un chat existente
	 * 
	 * Si existe un chat asociado a la misma id sobreescribe los datos
	 * @param chat: Nuevos datos del chat
	 */
	public void updateChat(Chat chat) {
		this.jdbcTemplate.update("update chat "
				+ "set user_one = ?,"
				+ " user_two = ?"
				+ " where chat_id = ?",
				chat.getNickUserOne(), chat.getNickUserTwo(), chat.getChatId() );
	}

	/**Borra de la base de datos el chat asociado a la id dada
	 * 
	 * @param chat_id: Id asociada al chat que se desea borrar
	 */
	public void deleteChat(int chat_id) {
		this.jdbcTemplate.update("DELETE FROM Chat WHERE chat_id = ?", chat_id);
	}
	
	
	public int addChatAndReturnId(Chat ch){
		KeyHolder holder = new GeneratedKeyHolder();
		
		final Chat chat = ch;
		
		this.jdbcTemplate.update(
				new PreparedStatementCreator() {           

	                @Override
	                public PreparedStatement createPreparedStatement(Connection connection)
	                        throws SQLException {
	                	String sql = "insert into Chat( user_one, user_two) "
	            				+ "values(?, ?)";
	                    PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
	                    ps.setString(1, chat.getNickUserOne());
	                    ps.setString(2, chat.getNickUserTwo());
	                    return ps;
	                }
	            }, holder);
		
		return (Integer)holder.getKeys().get("chat_id");
	}
}
