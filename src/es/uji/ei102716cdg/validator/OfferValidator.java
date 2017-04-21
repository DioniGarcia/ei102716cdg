package es.uji.ei102716cdg.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei102716cdg.domain.collaboration.Offer;

public class OfferValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Offer.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errs) {
		Offer offer = (Offer)obj;
		
		//Check skill id
		if (offer.getDescription().trim().equals(""))
			errs.rejectValue("description", "obligatorio",
					"La oferta debe tener una descripción");

	}
	
}
