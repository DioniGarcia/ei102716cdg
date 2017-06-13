package es.uji.ei102716cdg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

import es.uji.ei102716cdg.dao.SkillDao;
import es.uji.ei102716cdg.dao.StudentDao;
import es.uji.ei102716cdg.dao.UserProvider;
import es.uji.ei102716cdg.domain.collaboration.Offer;
import es.uji.ei102716cdg.domain.collaboration.Request;
import es.uji.ei102716cdg.domain.skill.Skill;
import es.uji.ei102716cdg.domain.user.Student;
import es.uji.ei102716cdg.util.Email;



@Service
public class EmailService {


	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private UserProvider userProvider;
	
	@Autowired
	private SkillDao skillDao;
	
	
	public void sendEmailOfferAccepted(Offer offer, String nick){
		String email = studentDao.getStudent(nick).getEmail();
		Skill skill = skillDao.getSkill(offer.getSkill_Id());
		String title = "[Skill Sharing] Tu oferta de " + skill.getName() + " - " + skill.getDescription() + " ha sido aceptada";
		
		String message = "Un usuario ha aceptado una oferta publicada por ti y se ha establecido una colaboración.";
		
		Email.send(email, title, message);
	}
	
	public void sendEmailRequestAccepted(Request request, String nick){
		String email = studentDao.getStudent(nick).getEmail();
		Skill skill = skillDao.getSkill(request.getSkill_Id());
		String title = "[Skill Sharing] Tu demanda de " + skill.getName() + " - " + skill.getDescription() + " ha sido aceptada";
		
		String message = "Un usuario ha aceptado una demanda publicada por ti y se ha establecido una colaboración.";
		
		Email.send(email, title, message);
	}
	
	public void sendEmailPassword(String nick){
		int randPass = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);
		String newPassword = Integer.toString(randPass);
		
		String title = "[Skill Sharing] Recuperacion de contraseña";
		String message = "Has solicitado una nueva contraseña para el sitio web Skill Sharing. "
						+ "Tu nueva contraseña es: " + newPassword;
		
		Student student = studentDao.getStudent(nick);
		String email = student.getEmail();
		
		student.setPasswd(userProvider.encodePassword(newPassword));
		studentDao.updateStudent(student);
		
		Email.send(email, title, message);
	}
}
