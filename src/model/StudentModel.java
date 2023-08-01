package model;

public class StudentModel {

	private int rollNo;
	private String name;
	private String gender;
	private String className;
	private String department;
	private String dob;
	private int contactNo;
	private String bloodGroup;
	private int year;
	public StudentModel(int rollNo, String name, String gender, String className, String department, String dob,
			int contactNo, String bloodGroup, int year) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.gender = gender;
		this.className = className;
		this.department = department;
		this.dob = dob;
		this.contactNo = contactNo;
		this.bloodGroup = bloodGroup;
		this.year = year;
	}
	public StudentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public int getContactNo() {
		return contactNo;
	}
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
	
}
