package es.uji.ei102716cdg.controller;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei102716cdg.domain.Student;

public class StudentValidator implements Validator {

	static String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
	
	@Override
	public boolean supports(Class<?> cls) {
		return Student.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errs) {
		Student student = (Student)obj;
		
		//Check username
		if (student.getUserName().trim().equals(""))
			errs.rejectValue("userName", "obligatorio",
					"Usuario obligatorio");
		else if (student.getUserName().length()>15)
			errs.rejectValue("userName", "longitud",
					"Usuario demasiado largo");
			
		//Check DNI
		if(student.getNif().length() != 9 ) 
			errs.rejectValue("nif", "obligatorio",
					"DNI obligatorio");
		else {
			char expected_letter = ' ';
			try{
				expected_letter = LETRAS_DNI.charAt(Integer.parseInt(student.getNif().substring(0,8)) % 23);
				if (! (expected_letter == student.getNif().charAt(8))) 
					errs.rejectValue("nif", "obligatorio",
							"DNI invalido");
			} catch (NumberFormatException nfe){
				errs.rejectValue("nif", "invalido",
						"DNI invalido");
			}			
		}
			
		
		//Check name
		if(student.getName().length()>50)
			errs.rejectValue("name", "longitud",
					"Nombre demasiado largo");
		
		//Check email
		if(student.getEmail().trim().equals(""))
			errs.rejectValue("email", "obligatorio",
					"Email obligatorio");
		else if (student.getEmail().length()>25)
			errs.rejectValue("email", "longitud",
					"Email invalido");
		
		//Check password
		if (student.getPasswd().trim().length()<6)
			errs.rejectValue("passwd", "obligatorio",
					"Contraseña demasiado corta");
	}
	
}
