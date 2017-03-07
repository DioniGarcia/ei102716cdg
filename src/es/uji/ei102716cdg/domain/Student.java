package es.uji.ei102716cdg.domain;

public class Student {
	private String nif;
	private String name;
	private String email;
	private String userName;
	private String passwd;
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	@Override
	public String toString() {
		return "Student [nif=" + nif + ", name=" + name + ", email=" + email + ", userName=" + userName + ", passwd="
				+ passwd + "]";
	}

	
}
