package model;

public class StudentAcademic {

	private int rollNo;
	private int studentrank;
	private Float cgpa;
	private int attendance;
	private int passYear;
	private int arrear;
	public StudentAcademic(int rollNo, int studentrank, Float cgpa, int attendance, int passYear, int arrear) {
		super();
		this.rollNo = rollNo;
		this.studentrank = studentrank;
		this.cgpa = cgpa;
		this.attendance = attendance;
		this.passYear = passYear;
		this.arrear = arrear;
	}
	public StudentAcademic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public int getStudentrank() {
		return studentrank;
	}
	public void setStudentrank(int studentrank) {
		this.studentrank = studentrank;
	}
	public Float getCgpa() {
		return cgpa;
	}
	public void setCgpa(Float cgpa) {
		this.cgpa = cgpa;
	}
	public int getAttendance() {
		return attendance;
	}
	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}
	public int getPassYear() {
		return passYear;
	}
	public void setPassYear(int passYear) {
		this.passYear = passYear;
	}
	public int getArrear() {
		return arrear;
	}
	public void setArrear(int arrear) {
		this.arrear = arrear;
	}
	
}
