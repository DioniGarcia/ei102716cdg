package es.uji.ei102716cdg.domain.user;

public class User {

	private String nick;		//Nombre de usuario UNICO, identificativo del usuario
	private String email;		//Email identificativo del usuario, también UNICO
	private String passwd;		//Contraseña
	
	//Getters y Setters
		public String getNick() {
			return nick;
		}
		public void setNick(String nick) {
			this.nick = nick;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPasswd() {
			return passwd;
		}
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
		
		//To String
		@Override
		public String toString() {
			return "User [nick=" + nick + ", email=" + email + ", passwd=" + passwd + "]";
		}
		
	
}
