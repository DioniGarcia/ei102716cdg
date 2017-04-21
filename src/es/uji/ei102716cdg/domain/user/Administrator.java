package es.uji.ei102716cdg.domain.user;

public class Administrator extends User{

	@Override
	public String toString() {
		return "Administrator [nick=" + super.getNick() + ", email=" + super.getEmail() 
		+ ", passwd=" + super.getPasswd() + "]";
	}

}
