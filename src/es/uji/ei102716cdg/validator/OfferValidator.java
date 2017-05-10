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
		
		if (offer.getSkill_Id() == 0)
			errs.rejectValue("skill_Id", "skill",
					"Debes seleccionar una habilidad");

		if (offer.getDescription().trim().equals(""))
			errs.rejectValue("description", "obligatorio",
					"La oferta debe tener una descripción");
		
		if (offer.getStartDate() == null){
			errs.rejectValue("startDate", "posterior",
					"Debes introducir una fecha de inicio");
		}
		
		if (offer.getEndDate() == null){
			errs.rejectValue("endDate", "posterior",
					"Debes introducir una fecha fin");
		}
		
		if ((offer.getStartDate() != null && offer.getEndDate() != null) && offer.getStartDate().after(offer.getEndDate()))
			errs.rejectValue("endDate", "posterior",
					"La fecha fin debe ser posterior a fecha inicio");

	}
	
}
