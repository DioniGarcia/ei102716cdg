package es.uji.ei102716cdg.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei102716cdg.domain.collaboration.Request;

public class RequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Request.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errs) {
		Request request = (Request)obj;
		
		//Check skill id
		if (request.getDescription().trim().equals(""))
			errs.rejectValue("description", "obligatorio",
					"La demanda debe tener una descripción");

	}
	
}
