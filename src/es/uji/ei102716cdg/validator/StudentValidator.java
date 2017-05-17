package es.uji.ei102716cdg.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei102716cdg.domain.user.Student;

public class StudentValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cls) {
		return Student.class.equals(cls);
	}

	@Override
	public void validate(Object obj, Errors errs) {
		Student student = (Student)obj;
		
		//Check nick name
		if (student.getNick().trim().equals(""))
			errs.rejectValue("nick", "obligatorio",
					"Nombre de usuario obligatorio");
		else if (student.getNick().length()>15)
			errs.rejectValue("nick", "longitud",
					"El nombre de usuario no puede superar 15 caracteres");
			
		//Check Dni
		if(student.getDni().trim().equals("")) 
			errs.rejectValue("dni", "obligatorio",
					"DNI obligatorio");
		else {
			validateDni(student.getDni(), errs);
		}
			
		//Check name
		if(student.getName().length()>50)
			errs.rejectValue("name", "longitud",
					"Nombre demasiado largo");
		
		//Check email
		if(student.getEmail().trim().equals(""))
			errs.rejectValue("email", "obligatorio",
					"Email obligatorio");
		else if (student.getEmail().length()>25)
			errs.rejectValue("email", "longitud",
					"Email invalido");
		
		//Check password
		if (student.getPasswd().trim().length()<6)
			errs.rejectValue("passwd", "obligatorio",
					"Contraseña demasiado corta");
		
	}
	

	//Funciones complementarias:
	/**Comprueba si el Dni dado es valido
	 * 
	 * Los numeros del Dni se establecen en funci�n a ciertos parametros:
	 *  - Est� compuesto por 9 elementos 
	 *  - Los primero 8 elementos son numeros, el ultimo es una letra
	 *  - La letra debe estar contenida entre las siguientes: T, R, W, A, G, M, Y, F, P, D, X, B, N, J, Z, S, Q, V, H, L, C, K, E
	 *  - La letra se calcula dividiendo los ocho d�gitos entre 23, el resto indica qu� letra corresponde
	 * 
	 * @param 	dni: Dni que se desea validar
	 * @return	true si el Dni cumple las condiciones de validaci�n 
	 */
	private void validateDni(String dni, Errors errs){
		int 		numDni 		= 0;							//Numero completo que forman parte del Dni 
		int 		module 		= 0;							//Modulo 23 de los numeros que forman el dni 
		char 		actualChar  = ' ';							//Variable reservada para almacenar cada uno de los caracteres del Dni
		char[]		numbers		= {'0','1','2','3','4','5',		//Numeros del 0 al 9 para comprobar si la letra es un numero
								   '6','7','8','9'};
		char[] 		valid_words = {'T','R','W','A','G','M',		//Listado de letras validas en el orden establecido por norma
								   'Y','F','P','D','X','B',
								   'N','J','Z','S','Q','V',
								   'H','L','C','K','E'};
				
		
		
		//Se comprueba que el dni est� compuesto por 9 caracteres
		if(dni.length() != 9 ){
			errs.rejectValue("dni", "obligatorio",
					"El Dni debe estar compuesto por 9 caracteres, prueba a añadir un 0 en la primera posición");
			return;
		}
			
		//Se comprueba que los 8 primeros elementos sean numeros y el ultimo una letra
		for (int i = 0; i < dni.length() -1; i++) {		
			actualChar = dni.charAt(i);
			
			boolean isANumber = false;
			for(char number : numbers){
				if(actualChar == number){
					isANumber = true;
					break;
				}
			}
			
			if(!isANumber){
				errs.rejectValue("dni", "ocho_numeros",
						"Los 8 primeros valores del Dni deben ser números entre 0 y 9");
				return;
			}
		}
		
		//Se comprueba si la letra concuerda con la opci�n correcta
		numDni = Integer.parseInt(dni.substring(0, dni.length()-1)); 
		module = numDni%23;
		
		actualChar = dni.charAt(dni.length()-1); //Ultimo caracter, es decir, la letra
		
		if( actualChar != valid_words[module]){
			errs.rejectValue("dni", "letra_invalida",
					"La letra introducida no pertenece a un Dni valido");
		}
						
	}
}
