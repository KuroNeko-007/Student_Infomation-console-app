package model;

public class StudentLoginModel {

	private String name;
	private String password;
	public StudentLoginModel(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public StudentLoginModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
