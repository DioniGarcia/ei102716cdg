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
		
		//Check ID
		if (offer.getSkill_Id() == 0)
			errs.rejectValue("skill_Id", "skill",
					"Debes seleccionar una habilidad");

		//Check Descripción
		if (offer.getDescription().trim().equals(""))
			errs.rejectValue("description", "obligatorio",
					"La oferta debe tener una descripción");
		
		//Check Fechas
		if (offer.getStartDate() == null)
			errs.rejectValue("startDate", "posterior",
					"Debes introducir una fecha de inicio");
		
		//Fecha inicio antes que hoy
		else if( offer.isBeforeToday() )
			errs.rejectValue("startDate", "inicioGeHoy",
					"La fecha de inicio debe ser posterior o igual a la fecha de hoy: ");
		
		if (offer.getEndDate() == null){
			errs.rejectValue("endDate", "posterior",
					"Debes introducir una fecha fin");
		}
		
		if (offer.getStartDate() != null && offer.getEndDate() != null){
			
			//Fecha fin antes que fecha inicio
			if( offer.getEndDate().before(offer.getStartDate()) )
				errs.rejectValue("endDate", "finalGtInicio",
						"La fecha fin debe ser posterior a fecha inicio");
			
		}
		
	}
	
}
