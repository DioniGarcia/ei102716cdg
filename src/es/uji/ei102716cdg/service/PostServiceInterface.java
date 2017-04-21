package es.uji.ei102716cdg.service;

import java.util.List;

import es.uji.ei102716cdg.domain.collaboration.Offer;
import es.uji.ei102716cdg.domain.collaboration.Request;
import es.uji.ei102716cdg.domain.skill.Skill;

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
	
	

	
}
