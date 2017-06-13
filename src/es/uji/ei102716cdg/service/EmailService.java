package es.uji.ei102716cdg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

import es.uji.ei102716cdg.dao.StudentDao;
import es.uji.ei102716cdg.dao.UserProvider;
import es.uji.ei102716cdg.domain.collaboration.Offer;
import es.uji.ei102716cdg.domain.collaboration.Request;
import es.uji.ei102716cdg.domain.user.Student;
import es.uji.ei102716cdg.util.Email;



@Service
public class EmailService {


	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private UserProvider userProvider;
	
	
	public void sendEmailOfferAccepted(Offer offer, String nick){
		
	}
	
	public void sendEmailRequestAccepted(Request request, String nick){
		
	}
	
	public void sendEmailPassword(String nick){
		int randPass = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);
		String newPassword = Integer.toString(randPass);
		
		String title = "[Skill Sharing] Recuperacion de contraseña";
		String message = "Has solicitado una nueva contraseña para el sitio web Skill Sharing. <br/>"
						+ "Tu nueva contraseña es: <b>" + newPassword +"</b>";
		
		Student student = studentDao.getStudent(nick);
		String email = student.getEmail();
		
		student.setPasswd(userProvider.encodePassword(newPassword));
		studentDao.updateStudent(student);
		
		Email.send(email, title, message);
	}
}
