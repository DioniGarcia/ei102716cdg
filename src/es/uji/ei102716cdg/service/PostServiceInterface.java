package es.uji.ei102716cdg.service;

import java.util.List;

import es.uji.ei102716cdg.domain.chat.Chat;
import es.uji.ei102716cdg.domain.collaboration.Offer;
import es.uji.ei102716cdg.domain.collaboration.Post;
import es.uji.ei102716cdg.domain.collaboration.Request;
import es.uji.ei102716cdg.domain.skill.Skill;
import es.uji.ei102716cdg.domain.user.Student;
import es.uji.ei102716cdg.domain.user.User;

public interface PostServiceInterface {
	
	/**Devuelve un listado con el nick de todos los estudiantes activos en el sistema
	 * 
	 * @return listado de nick de estudiantes 
	 */
	public List<String> getActiveStudentsNick();
	
	/**Devuelve un listado con todas las skills activas en el sistema
	 * 
	 * @return listado de skills
	 */
	public List<Skill> getActiveSkills();
	
	/**Devuelve un listado con todas las ofertas activas en el sistema
	 * 
	 * @return listado de ofertas
	 */
	public List<Offer> getActiveOffers();
	
	/**Devuelve un listado con todas las demandas activas en el sistema
	 * 
	 * @return listado de demandas
	 */
	public List<Request> getActiveRequests();

	/**Devuelve un listado con todas los chats existentes en el sistema
	 * 
	 * @return listado de chats
	 */
	public List<Chat> getChats();
	
	/** Devuelve las skills asociadas a una lista de posts (Ofertas o Demandas)
	 * 
	 * @param list: list de ofertas o demandas 
	 * @return lista de skills
	 */
	public List<Skill> getSkillsByPost(List<? extends Post> list);
	
	/** Devuelde un listado de usuarios correspondiente a una lista de posts
	 * 
	 * Los indices de la lista devuelta corresponden con 
	 * los indices de la lista de la lista de posts dada
	 * 
	 * @param 	list: Lista de ofertas o demandas
	 * @return	lista de usuarios que han publicado las ofertas
	 */
	public List<User> getUsersByPost(List<? extends Post> list);
	
	/**Devuelve las ofertas ordenadas de más a menos recientes
	 * 
	 * @return	lista de ofertas de más a menos reciente
	 */
	public List<Offer> getRecentOffers();
	
	/**Devuelve las demandas ordenadas de más a menos recientes
	 * 
	 * @return	lista de demandas de más a menos reciente
	 */
	List<Request> getRecentRequests();
	
	public Skill getSkillById(int id);
	
	public Student getStudentByNick(String nick);

	
}
