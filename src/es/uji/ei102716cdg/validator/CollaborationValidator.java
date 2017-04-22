package es.uji.ei102716cdg.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei102716cdg.domain.collaboration.Collaboration;

public class CollaborationValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Collaboration.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errs) {
		Collaboration collaboration = (Collaboration)obj;
		
		//Check skill id
		if (collaboration.getRating() <= 0 || collaboration.getRating() > 5 )
			errs.rejectValue("rating", "outOfRange",
					"La colaboración debe puntuarse entre 1 y 5");

	}
	
}
