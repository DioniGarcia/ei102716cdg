package es.uji.ei102716cdg.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uji.ei102716cdg.dao.CollaborationDao;
import es.uji.ei102716cdg.dao.OfferDao;
import es.uji.ei102716cdg.dao.RequestDao;
import es.uji.ei102716cdg.dao.SkillDao;
import es.uji.ei102716cdg.dao.StudentDao;
import es.uji.ei102716cdg.domain.collaboration.Collaboration;
import es.uji.ei102716cdg.domain.collaboration.Offer;
import es.uji.ei102716cdg.domain.collaboration.Request;
import es.uji.ei102716cdg.domain.skill.Skill;
import es.uji.ei102716cdg.domain.user.Student;

@Service
public class StatsService implements StatsServiceInterface{
	
	@Autowired
	StudentDao studentDao;
	
	@Autowired
	SkillDao skillDao;
	
	@Autowired
	OfferDao offerDao;
	
	@Autowired
	RequestDao requestDao;
	
	@Autowired
	CollaborationDao collaborationDao;
	
	@Autowired
	PostService postService;
	
	@Autowired
	PointsService pointsService;
	
	@Override
	public int[] getPostStats() {
		int[] ret = new int[2];
		ret[0] = offerDao.getOffers().size();
		ret[1] = requestDao.getRequests().size();
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * @see es.uji.ei102716cdg.service.PostServiceInterface#getGeneralStats()
	 */
	
	@Override
	public int[][] getGeneralStats() {
		int[][] matrizStatsPorMeses = new int[3][11];
		String[] meses = 	{"Septiembre", "Octubre", "Noviembre", "Diciembre",
							 "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio"};
		int[] mesesInt = 	{8, 9, 10, 11,
				 0, 1, 2, 3, 4, 5, 6};
		List<Offer> offers = offerDao.getOffers();
		List<Request> requests = requestDao.getRequests();
		List<Collaboration> collabs = collaborationDao.getCollaborations();
		
		List<Offer> temp = new ArrayList<Offer>();
		for (int i = 0 ; i < 11 ; i++){
			for (Offer offer : offers){
				if (offer.getStartDate().getMonth() == mesesInt[i])
					temp.add(offer);
			}
			matrizStatsPorMeses[0][i] = temp.size();
			temp.clear();
		}
		
		List<Collaboration> temp2 = new ArrayList<Collaboration>();
		for (int i = 0 ; i < 11 ; i++){
			for (Collaboration collaboration : collabs){
				Date offerDate = postService.getOffer(offers,collaboration.getOffer_id()).getStartDate();
				Date requestDate = postService.getRequest(requests,collaboration.getRequest_id()).getStartDate();
				if (offerDate.after(requestDate)){
					if (offerDate.getMonth() == mesesInt[i])
						temp2.add(collaboration);
				} else {
					if (requestDate.getMonth() == mesesInt[i])
						temp2.add(collaboration);
				}
			}
			matrizStatsPorMeses[1][i] = temp2.size();
			temp2.clear();
		}
		
		List<Request> temp3 = new ArrayList<Request>();
		for (int i = 0 ; i < 11 ; i++){
			for (Request request : requests){
				if (request.getStartDate().getMonth() == mesesInt[i])
					temp3.add(request);
			}
			matrizStatsPorMeses[2][i] = temp3.size();
			temp3.clear();
		}
		return matrizStatsPorMeses;
	}
	
	

	@Override
	public List<String> getHotSkills() {
		List<Collaboration> collabs = collaborationDao.getCollaborations();
		List<Skill> skills = skillDao.getSkills();
		List<Offer> offers = offerDao.getOffers();
		List<String> ret = new ArrayList<String>();
		
		SortedMap<Integer, Integer> hotSkills = new TreeMap<Integer, Integer>();
		
		for (Collaboration collab : collabs){
			Integer skillId = postService.getOffer(offers, collab.getOffer_id()).getSkill_Id();
			Integer count = hotSkills.get(skillId);
		      if (count != null) {
		    	  hotSkills.put(skillId, count + 1);
		      } else {
		    	  hotSkills.put(skillId, 1);
		      }
		}
		
		List<Entry<Integer, Integer>> greatest = findGreatest(hotSkills, 5);
		Skill skill;
		for (Entry<Integer, Integer> entry : greatest)
        {
			skill = postService.getSkillById(skills, entry.getKey());
            ret.add(skill.getName() + ": " + skill.getDescription() +"#"+ entry.getValue());
        }
		return ret;
	}

	@Override
	public List<String> getColdSkills() {
		List<Collaboration> collabs = collaborationDao.getCollaborations();
		List<Skill> skills = skillDao.getSkills();
		List<Offer> offers = offerDao.getOffers();
		List<String> ret = new ArrayList<String>();
		
		SortedMap<Integer, Integer> hotSkills = new TreeMap<Integer, Integer>();
		
		for (Collaboration collab : collabs){
			Integer skillId = postService.getOffer(offers, collab.getOffer_id()).getSkill_Id();
			Integer count = hotSkills.get(skillId);
		      if (count != null) {
		    	  hotSkills.put(skillId, count + 1);
		      } else {
		    	  hotSkills.put(skillId, 1);
		      }
		}
		
		List<Entry<Integer, Integer>> greatest = findLowest(hotSkills, 5);
		Skill skill;
		for (Entry<Integer, Integer> entry : greatest)
        {
			skill = postService.getSkillById(skills, entry.getKey());
            ret.add(skill.getName() + ": " + skill.getDescription() +"#"+ entry.getValue());
        }
		return ret;
	}

	
	private static <K, V extends Comparable<? super V>> List<Entry<K, V>> 
    findGreatest(Map<K, V> map, int n)
	{
	    Comparator<? super Entry<K, V>> comparator = 
	        new Comparator<Entry<K, V>>()
	    {
	        @Override
	        public int compare(Entry<K, V> e0, Entry<K, V> e1)
	        {
	            V v0 = e0.getValue();
	            V v1 = e1.getValue();
	            return v0.compareTo(v1);
	        }
	    };
	    PriorityQueue<Entry<K, V>> highest = 
	        new PriorityQueue<Entry<K,V>>(n, comparator);
	    for (Entry<K, V> entry : map.entrySet())
	    {
	        highest.offer(entry);
	        while (highest.size() > n)
	        {
	            highest.poll();
	        }
	    }

	    List<Entry<K, V>> result = new ArrayList<Map.Entry<K,V>>();
	    while (highest.size() > 0)
	    {
	        result.add(highest.poll());
	    }
	    return result;
}
	
	private static <K, V extends Comparable<? super V>> List<Entry<K, V>> 
    findLowest(Map<K, V> map, int n)
	{
	    Comparator<? super Entry<K, V>> comparator = 
	        new Comparator<Entry<K, V>>()
	    {
	        @Override
	        public int compare(Entry<K, V> e0, Entry<K, V> e1)
	        {
	            V v0 = e0.getValue();
	            V v1 = e1.getValue();
	            return -v0.compareTo(v1);
	        }
	    };
	    PriorityQueue<Entry<K, V>> highest = 
	        new PriorityQueue<Entry<K,V>>(n, comparator);
	    for (Entry<K, V> entry : map.entrySet())
	    {
	        highest.offer(entry);
	        while (highest.size() > n)
	        {
	            highest.poll();
	        }
	    }

	    List<Entry<K, V>> result = new ArrayList<Map.Entry<K,V>>();
	    while (highest.size() > 0)
	    {
	        result.add(highest.poll());
	    }
	    return result;
}

	@Override
	public Integer getNumeroUsuarios() {
		return studentDao.getStudents().size();
	}

	@Override
	public Integer getMediaPuntos() {
		List<Student> students = studentDao.getStudents();
		int suma = 0;
		for (Student student : students)
			suma += pointsService.getUserPoints(student.getNick());
		return suma / students.size();
	}
}
