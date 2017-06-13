package es.uji.ei102716cdg.service;

import java.util.List;

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
	public List<Student> getStudentsByPost(List<? extends Post> list);
	
	/**Devuelve las ofertas ordenadas de más a menos recientes
	 * 
	 * @return	lista de ofertas de más a menos reciente
	 */
	public List<Offer> getRecentOffers();
	
	/**Devuelve una lista de ofertas paginada
	 * 
	 * @return lista de ofertas paginada
	 */
	
	public List<Offer> getPaginatedOffers(List<Offer> offers, int pageSize, int offset, String nick);
	
	/**Devuelve una lista de demandas paginada
	 * 
	 * @return lista de demandas paginada
	 */
	
	public List<Request> getPaginatedRequests(List<Request> requests, int pageSize, int offset, String nick);
	
	/**Devuelve las demandas ordenadas de más a menos recientes
	 * 
	 * @return	lista de demandas de más a menos reciente
	 */
	
	List<Request> getRecentRequests();
	
	/**Devuelve la skill con el id pasado
	 * 
	 * @param id
	 * @return skill 
	 */
	
	public Skill getSkillById(int id);
	
	
	/**Devuelve el estudiante a partir de su nick
	 * 
	 * @param nick
	 * @return estudiante con el nick pasado
	 */
	public Student getStudentByNick(String nick);
	
	
	/**Devuelve las demandas de un estudiante que tienen la skillId igual a la skillid pasada
	 * 
	 * @param nick
	 * @param skillId
	 * @return lista de demandas
	 */
	public List<Request> getRequestsBySkillId(String nick, int skillId);
	
	
	/**Devuelve las ofertas de un estudiante que tienen la skillId igual a la skillId pasada
	 * 
	 * @param nick
	 * @param skillId
	 * @return lista de ofertas
	 */
	public List<Offer> getOffersBySkillId(String nick, int skillId);

	/**Crea una colaboracion a partir del id de oferta y demanda
	 * 
	 * @param offerId
	 * @param requestId
	 */
	public void addCollaboration(int offerId, int requestId);
	
	
	/** Anyade una demanda y ademas devuelve el id de esa demanda en la bbdd
	 * 
	 * @param request: objeto oferta sin atributo id setteado
	 * @return id de la demanda creada
	 */
	public int addRequestAndGetId(Request request);
	
	/** Anyade una oferta y ademas devuelve el id de esa demanda en la bbdd
	 * 
	 * @param offer: objeto demanda sin atributo id setteado
	 * @return id de la demanda creada
	 */
	public int addOfferAndGetId(Offer offer);

	
	public Offer getOffer(int id);
	
	public Request getRequest(int id);
	
	public List<Collaboration> getCollaborations(String nick);
	public List<Collaboration> getActiveCollaborations(String nick);
	public List<Collaboration> getOldCollaborations(String nick);
	public List<Collaboration> getEvalCollaborations(String nick);

	
	/**Devuelve las habilidades asociadas a una lista de colaboraciones
	 * 
	 * @param collabs
	 * @return
	 */
	public List<Skill> getSkillsByCollabs(List<Collaboration> collabs);

	
	/**Devuelve las fechas fin asociadas a una lista de colaboraciones
	 * 
	 * @param collabs
	 * @return
	 */
	public List<String> getCollabEndDates(List<Collaboration> collabs);
	
	public int getOffersPageCount(List<Offer> offers, int pageElements, String nick);
	public int getRequestsPageCount(List<Request> requests, int pageElements, String nick);
	
	
	/**Extrae de la Db las n ofertas con inicio más reciente, que sean activas y no pertenezcan al usuario
	 * 
	 * Si se quieren extraer todas las ofertas, "number" debe ser igual o inferior que 0
	 * 
	 * @param number:	Numero de ofertas a extraer, o number < 0 para extraer todas
	 * @param nick:		Nick del usuario que verá las ofertas extraidas
	 * @return
	 */
	List<Offer> getActiveRecentOffers(int number, String nick);
	
	/**Extrae de la Db las n demandas con inicio más reciente, que sean activas y no pertenezcan al usuario
	 * 
	 * Si se quieren extraer todas las demandas, "number" debe ser igual o inferior que 0
	 * 
	 * @param number:	Numero de demandas a extraer, o number < 0 para extraer todas
	 * @param nick:		Nick del usuario que verá las demandas extraidas
	 * @return
	 */
	List<Request> getActiveRecentRequests(int number, String nick);
	
	
	public int getRating(String nick);

	public List<Integer> getRatingByStudents(List<Student> students);
	
		
	public List<Student> getStudentsByCollabsOf( List<Collaboration> collabs);
	public List<Student> getStudentsByCollabsRq( List<Collaboration> collabs);
	
	public List<Offer> searchOffers(String query, String nick);
	public List<Request> searchRequests(String query, String nick);

	List<Offer> getMyOffers(String nick);

	List<Request> getMyRequests(String nick);
	
}
