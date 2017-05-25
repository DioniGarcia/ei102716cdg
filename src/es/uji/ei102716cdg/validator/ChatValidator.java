package es.uji.ei102716cdg.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei102716cdg.domain.chat.Chat;

public class ChatValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Chat.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errs) {
		Chat chat = (Chat)obj;
		
		//Check student nicks
		if (chat.getNickUserOne() == chat.getNickUserTwo()){
			errs.rejectValue("student nicks", "sameNick",
					"El chat debe establecerse entre dos usuarios distintos, no pude existir un canal con uno mismo");
		}
		
	}
	
}
