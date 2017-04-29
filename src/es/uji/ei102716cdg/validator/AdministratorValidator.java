package es.uji.ei102716cdg.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei102716cdg.domain.user.Administrator;

public class AdministratorValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cls) {
		return Administrator.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errs) {
		Administrator administrator = (Administrator)obj;
		
		//Check nick name
		if (administrator.getNick().trim().equals(""))
			errs.rejectValue("nick", "obligatorio",
					"Nombre de usuario obligatorio");
		else if (administrator.getNick().length()>15)
			errs.rejectValue("nick", "longitud",
					"El nombre de usuario no puede superar 15 caracteres");
			
		//Check email
		if(administrator.getEmail().trim().equals(""))
			errs.rejectValue("email", "obligatorio",
					"Email obligatorio");
		else if (administrator.getEmail().length()>25)
			errs.rejectValue("email", "longitud",
					"Email invalido");
		
		//Check password
		if (administrator.getPasswd().trim().length()<6)
			errs.rejectValue("passwd", "obligatorio",
					"Contraseña demasiado corta");
		
	}
}
