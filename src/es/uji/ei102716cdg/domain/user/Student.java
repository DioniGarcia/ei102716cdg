package es.uji.ei102716cdg.domain.user;

public class Student extends User{
	private String name;		//Nombre y apellidos
	private String dni;			//DNI asociado al nombre, 8 digitos
	private int    avatar;		//Id del avatar
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getAvatar() {
		return avatar;
	}
	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}
	
	//To String
	@Override
	public String toString() {
		return "Student [nick=" + super.getNick() + ", email=" + super.getEmail() 
		+ ", passwd=" + super.getPasswd() + ", name=" + name + ", dni=" + dni + ", avatar=" + avatar + "]";
	}
	
}
