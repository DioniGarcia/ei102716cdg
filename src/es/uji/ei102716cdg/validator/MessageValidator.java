package es.uji.ei102716cdg.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei102716cdg.domain.chat.Message;
import es.uji.ei102716cdg.domain.collaboration.Offer;

public class MessageValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Offer.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errs) {
		Message message = (Message)obj;
		
		//Check skill id
		if (message.getContent().trim().equals(""))
			errs.rejectValue("contenido", "obligatorio",
					"No puede enviarse un mensaje vacio, ponle un emoji o algo");

	}
	
}
