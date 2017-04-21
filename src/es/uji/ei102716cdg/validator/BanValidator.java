package es.uji.ei102716cdg.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei102716cdg.domain.user.Ban;

public class BanValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cls) {
		return Ban.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errs) {
		Ban ban = (Ban)obj;
		
		//Check student nick name
		if (ban.getStudent_nick().trim().equals(""))
			errs.rejectValue("nick", "obligatorio",
					"El nombre de usuario asociado al baneo es obligatorio");
		//Seria interesante que el nombre de usuario estiviese entre los existentes

	}
}
