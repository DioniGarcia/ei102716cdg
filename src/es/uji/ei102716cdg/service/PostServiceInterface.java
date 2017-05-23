package es.uji.ei102716cdg.service;

import java.util.List;

import es.uji.ei102716cdg.domain.chat.Chat;
import es.uji.ei102716cdg.domain.collaboration.Collaboration;
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
	/**Devuelve un listado con el numero de skills activas seleccionado
	 * 
	 * @return listado de skills
	 */
	public List<Skill> getActiveSkills(int number);

	
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
	
	/**Devuelve una lista de ofertas paginada
	 * 
	 * @return lista de ofertas paginada
	 */
	
	public List<Offer> getPaginatedOffers(int pageSize, int offset);
	
	/**Devuelve una lista de demandas paginada
	 * 
	 * @return lista de demandas paginada
	 */
	
	public List<Request> getPaginatedRequests(int pageSize, int offset);
	
	/**Devuelve las demandas ordenadas de más a menos recientes
	 * 
	 * @return	lista de demandas de más a menos reciente
	 */
	
	List<Request> getRecentRequests();
	
	public Skill getSkillById(int id);
	
	public Student getStudentByNick(String nick);
	
	public List<Request> getRequestsBySkillId(String nick, int skillId);

	public List<Offer> getOffersBySkillId(String nick, int skillId);
	
	public void addCollaboration(int offerId, int requestId);
	
	public int addRequestAndGetId(Request request);
	
	public int addOfferAndGetId(Offer offer);

	
	public Offer getOffer(int id);
	
	public Request getRequest(int id);
	
	public List<Collaboration> getCollaborations(String nick);
	public List<Collaboration> getActiveCollaborations(String nick);
	public List<Collaboration> getOldCollaborations(String nick);
	public List<Collaboration> getEvalCollaborations(String nick);

	public List<Skill> getSkillsByCollabs(List<Collaboration> collabs);

	public List<String> getCollabEndDates(List<Collaboration> collabs);
	
	public int getOffersPageCount(int size);
	public int getRequestsPageCount(int size);
	
	public int getUserPoints(String nick);
	
	public int getReceivedHours(String nick);
	public int getOfferedHours(String nick);

	List<Offer> getActiveRecentOffers(int number, String nick);
	List<Request> getActiveRecentRequests(int number, String nick);
	
	public int[] getPostStats();

	public int[][] getGeneralStats();


	
	
}
