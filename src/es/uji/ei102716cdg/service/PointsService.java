package es.uji.ei102716cdg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uji.ei102716cdg.dao.CollaborationDao;
import es.uji.ei102716cdg.dao.OfferDao;
import es.uji.ei102716cdg.dao.RequestDao;
import es.uji.ei102716cdg.dao.SkillDao;
import es.uji.ei102716cdg.domain.collaboration.Collaboration;
import es.uji.ei102716cdg.domain.collaboration.Offer;
import es.uji.ei102716cdg.domain.collaboration.Request;

@Service
public class PointsService implements PointsServiceInterface {
	
	@Autowired
	SkillDao skillDao;
	
	@Autowired
	OfferDao offerDao;
	
	@Autowired
	RequestDao requestDao;
	
	@Autowired
	CollaborationDao collaborationDao;
	
	@Override
	public int getUserPoints(String nick) {
		int sum = 20;
		Offer offer = null;
		List<Collaboration> collabs = collaborationDao.getAllCollaborationsByNick(nick);
		for (Collaboration col : collabs){
			offer = offerDao.getOffer(col.getOffer_id());
			
			short totalHours = col.getTotalHours();
			
			if ( offer.getStudent_nick().equals(nick)){
				short rating = col.getRating();
				if (rating >= 3)
					sum += totalHours * Math.pow(1.12,  rating - 3f); // factor beneficioso 
				else
					sum += totalHours * Math.pow(0.88,  3f - rating); // factor penalizador
			
			} else { // demandante
				sum -= totalHours;
			}
		}
		
		sum += offerDao.getOffersByNick(nick).size() * 2;
		
		return sum;
	}

	@Override
	public int getReceivedHours(String nick) {
		
		int sum = 0;
		
		Request request = null;
		List<Collaboration> collabs = collaborationDao.getAllCollaborationsByNick(nick);
		for (Collaboration col : collabs){
			request = requestDao.getRequest(col.getRequest_id());
			
			if (  request.getStudent_nick().equals(nick)){
				sum += col.getTotalHours();
			} 
		}
		
		return sum;
	}

	@Override
	public int getOfferedHours(String nick) {
		int sum = 0;
		
		Offer offer = null;
		List<Collaboration> collabs = collaborationDao.getAllCollaborationsByNick(nick);
		for (Collaboration col : collabs){
			offer = offerDao.getOffer(col.getOffer_id());
			
			if ( offer.getStudent_nick().equals(nick)){
				sum += col.getTotalHours();
			} 
		}
		
		return sum;
	}

}
