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
		
		//Check ID
		if (request.getSkill_Id() == 0)
			errs.rejectValue("skill_Id", "skill",
					"Debes seleccionar una habilidad");

		//Check Descripción
		if (request.getDescription().trim().equals(""))
			errs.rejectValue("description", "obligatorio",
					"La demanda debe tener una descripción");
		
		//Check Fechas
		if (request.getStartDate() == null)
			errs.rejectValue("startDate", "posterior",
					"Debes introducir una fecha de inicio");
		
		//Fecha inicio antes que hoy
		else if( request.isBeforeToday())
			errs.rejectValue("startDate", "inicioGeHoy",
					"La fecha de inicio debe ser posterior o igual a la fecha de hoy: ");
		
		if (request.getEndDate() == null){
			errs.rejectValue("endDate", "posterior",
					"Debes introducir una fecha fin");
		}
		
		if (request.getStartDate() != null && request.getEndDate() != null){
			
			//Fecha fin antes que fecha inicio
			if( request.getEndDate().before(request.getStartDate()) )
				errs.rejectValue("endDate", "finalGtInicio",
						"La fecha fin debe ser posterior a fecha inicio");
			
		}
		
	}
	
}