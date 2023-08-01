package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.AcademicDao;
import dao.AdminDao;
import dao.StudentDao;
import model.Role;
import model.StudentAcademic;
import model.StudentModel;
import service.AdminService;
import service.StudentDetails;
import service.StudentService;


public class MainController {

    public static void main(String[] args) throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        String role;
        System.out.println("Student Information Management System");
        StudentDetails studentService = new StudentDetails();
        StudentDao studentDao = new StudentDao();
        AcademicDao academicDao =new AcademicDao();
        System.out.println("1) Admin \n2) Student \n");
        int login = sc.nextInt();
        if(login== 1) {
        	role=Role.admin.toString();
        sc.nextLine();
        AdminDao adminDao = new AdminDao();
        AdminService adminservice = new AdminService();
        System.out.print("Admin Username: ");
        String adminUsername = sc.nextLine();
        System.out.print("Admin Password: ");
        String adminPassword = sc.nextLine();
        boolean isAuthenticated = adminservice.authenticateAdmin(adminUsername, adminPassword);

        
        if (!isAuthenticated) {
            System.out.println("Authentication failed. Exiting the program.");
            sc.close();
            return;
        }


        while (true) {
            System.out.println("1) Add Student\n2) View Students\n3) Update Student by ID\n4) Search\n5) Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                	addStudent(sc, studentService,academicDao);
                    break;
                case 2:
                	 System.out.println("1) View All \n2) View By Department");
                	 int choice2=sc.nextInt();
                	 sc.nextLine();
                	 switch(choice2) {
                	 case 1:
                		 viewStudents(studentDao);
                		 break;                		 
                	 case 2:
                		 System.out.println("Enter Department Name");
                		 String dept=sc.nextLine();
                		ArrayList <StudentModel> stud=viewStudents(studentDao,dept);
                		for(StudentModel student:stud) {
                		System.out.println("+------+----------------------+--------------+----------------------+-----+---------------------+");
                        System.out.printf("| %-4s | %-10s | %-10s | %-10s | %-10s | %-10s | %-4s | %-10s | %-4s |%n", "ID", "Name", "Class","Gender", "Department", "DOB", "ContactNo", "BloodGroup", "Year of Study");
                        System.out.println("+-----------------------------------------------------------------------------------------------");
                        System.out.printf("| %-4d | %-10s | %-10s | %-10s | %-10s | %-10s | %-4d | %-10s | %-4s|%n",
                                student.getRollNo(), student.getName(),student.getGender(), student.getClassName(), student.getDepartment(),
                                student.getDob(), student.getContactNo(), student.getBloodGroup(), student.getYear());
                    System.out.println("+------+----------------------+--------------+----------------------+-----+---------------------+");
                 
                		}
                		break;
                	 default :
                		 System.out.println("Invalid choice. Try again.");
                	 }
                	 break;
                case 3:
                    updateStudent(sc, studentService);
                    break;
                case 4:
                	searchStudent(sc,studentDao);
                	break;
                case 5:
                	sc.close();
                	System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
        else if (login==2){ 
        	role=Role.student.toString();
        	System.out.print("Student RollNo: ");
            int studentRollNo = sc.nextInt();
            sc.nextLine();
        	System.out.println("Student Name: ");
            String studentUsername = sc.nextLine();
            System.out.println("Student Password: ");
            String studentPassword = sc.nextLine();
            StudentService studentservice = new StudentService();
            boolean isAuthenticated = studentservice.authenticateStudent(studentUsername, studentPassword);

            
            if (!isAuthenticated) {
                System.out.println("Authentication failed. Exiting the program.");
                sc.close();
                return;
            }
            else {
            	while(true) {
            	System.out.println("1) View General Details\n2) View Academic Details\n3) Exit");
            	int choice3=sc.nextInt();
            	sc.nextLine();
            	switch(choice3) {
            	case 1:
            		
            		StudentModel student=getStudentById(studentRollNo);
            		System.out.println("+------+----------------------+--------------+----------------------+---------------+---------------------+");
            		System.out.printf("| %-4s | %-10s | %-10s | %-10s | %-10s | %-10s | %-4s | %-10s | %-4s |%n", "ID", "Name", "Class","Gender", "Department", "DOB", "ContactNo", "BloodGroup", "Year of Study");
            		System.out.println("+---------------------------------------------------------------------------------------------------------+");
            		System.out.printf("| %-4s | %-10s | %-10s | %-10s | %-10s | %-10s | %-4s | %-10s | %-4s|%n",
            				student.getRollNo(), student.getName(),student.getGender(), student.getClassName(), student.getDepartment(),
            				student.getDob(), student.getContactNo(), student.getBloodGroup(), student.getYear());
            		System.out.println("+------+----------------------+--------------+----------------------+---------------+----------------------+");
            		break;
            	case 2:
            		
            		StudentAcademic academic = academicDao.getStudentbyId(studentRollNo);
                	System.out.println("+------+----------------------+--------------+");
                    System.out.printf("| %-4s | %-10s | %-10s | %-10s | %-10s | %-10s %n", "ID", "studentrank", "cgpa","attendance", "passYear", "arrear");
                    System.out.println("+---------------------------------------------");
                    System.out.printf("| %-4s | %-10s | %-10s | %-10s | %-10s | %-10s  %n",
                    		academic.getRollNo(), academic.getStudentrank(),academic.getCgpa(), academic.getAttendance(), academic.getPassYear(),academic.getArrear());
                System.out.println("+------+-------------------+------+");
            	break;
            	case 3:
            		sc.close();
                	System.exit(0);
            	default :
            		System.out.println("Invalid choice. Try again.");
            	}
            	
            	}	
            }
        }
        else {
        	System.out.println("Enter valid Number");
        }
    }
    public static ArrayList<StudentModel> viewStudents(StudentDao studentDao,String dept){
    	return studentDao.getStudentsByCourse(dept);
    }
    private static void searchStudent(Scanner sc,StudentDao studentDao) {
    	System.out.println("1)search by ID \n2) Search by Name");
    	int choice = sc.nextInt();
    	sc.nextLine();
    	StudentModel student;
    	List<StudentModel> students;
        switch (choice) {
            case 1:
            	System.out.print("Enter Search ID \n");
            	int studentRollNo=sc.nextInt();
            	sc.nextLine();
            	 student=getStudentById(studentRollNo);
            	 studentdisplay(studentRollNo,sc);
                break;
            case 2:
            	System.out.print("Enter Search Name \n");
            	String studentName=sc.nextLine();
            	students=getStudentbyName(studentName,studentDao);
            	if(students.isEmpty()) {
            		System.out.println("No student in this Name. Try Again");
            		searchStudent(sc,studentDao);
            	}
            	else {
            		System.out.println("+------+----------------------+--------------+----------------------+-----+---------------------+");
                    System.out.printf("| %-4s | %-10s | %-10s | %-10s | %-10s | %-10s | %-4s | %-10s | %-4s |%n", "ID", "Name", "Class","Gender", "Department", "DOB", "ContactNo", "BloodGroup", "Year of Study");
                    
                    for (StudentModel student1 : students) {
                   	 System.out.println("+-----------------------------------------------------------------------------------------------");
                        System.out.printf("| %-4d | %-10s | %-10s | %-10s | %-10s | %-10s | %-4d | %-10s | %-4s|%n",
                                student1.getRollNo(), student1.getName(),student1.getGender(), student1.getClassName(), student1.getDepartment(),
                                student1.getDob(), student1.getContactNo(), student1.getBloodGroup(), student1.getYear());
                    }
                    System.out.println("+------+----------------------+--------------+----------------------+-----+---------------------+");
                   }
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }
    private static void studentdisplay(int id,Scanner sc) {
    	System.out.println("1)Display by General Details \n2) Display by Academic Details");
    	int choice = sc.nextInt();
    	sc.nextLine();
    	
        switch (choice) {
            case 1:
            	StudentModel student=getStudentById(id);
            	System.out.println("+------+----------------------+--------------+----------------------+-----+---------------------+");
                System.out.printf("| %-4s | %-10s | %-10s | %-10s | %-10s | %-10s | %-4s | %-10s | %-4s |%n", "ID", "Name", "Class","Gender", "Department", "DOB", "ContactNo", "BloodGroup", "Year of Study");
                System.out.println("+-----------------------------------------------------------------------------------------------");
                System.out.printf("| %-4d | %-10s | %-10s | %-10s | %-10s | %-10s | %-4d | %-10s | %-4s|%n",
                        student.getRollNo(), student.getName(),student.getGender(), student.getClassName(), student.getDepartment(),
                        student.getDob(), student.getContactNo(), student.getBloodGroup(), student.getYear());
            System.out.println("+------+----------------------+--------------+----------------------+-----+---------------------+");
         
            break;
            case 2:
            	AcademicDao academicDao = new AcademicDao();
            	StudentAcademic academic = academicDao.getStudentbyId(id);
            	System.out.println("+------+----------------------+--------------+");
                System.out.printf("| %-4s | %-10d | %-10s | %-10d | %-10d %n", "ID", "studentrank", "cgpa","attendance", "passYear", "arrear");
                System.out.println("+---------------------------------------------");
                System.out.printf("| %-4s | %-10d | %-10s | %-10d | %-10d %n",
                		academic.getRollNo(), academic.getStudentrank(),academic.getCgpa(), academic.getAttendance(), academic.getPassYear(),academic.getArrear());
            System.out.println("+------+-------------------+------+");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                studentdisplay(id,sc);
        }
    }
    private static StudentModel getStudentById(int rollNo) {
    	StudentDetails studentService = new StudentDetails();
    	StudentModel student =studentService.getStudentById(rollNo);
    	   	return student;
    }
    private static List<StudentModel> getStudentbyName(String name,StudentDao studentDao ) {
    	List<StudentModel> stu=new ArrayList<>();
    	ArrayList<StudentModel> students = studentDao.getAllStudents();
    	if (students.isEmpty()) {
            System.out.println("No students in Database.");
        } else {
        	for (StudentModel student : students) {
        		if(student.getName().equals(name)) {
        			stu.add(student);
           	 System.out.println("+-----------+--------------+");
                System.out.printf("| %-4d | %-10s %n",
                        student.getRollNo(), student.getName());
                }     	
           }
        	System.out.println("+-----------+--------------+");
        }
    	
    	return stu;
    }
    private static void addStudent(Scanner sc, StudentDetails studentService,AcademicDao academicDao) {
        System.out.print("Enter Student ID: ");
        int rollNo = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Student Class: ");
        String className = sc.nextLine();
        System.out.print("Enter Student Gender: ");
        String gender = sc.nextLine();

        System.out.print("Enter Student Department: ");
        String department = sc.nextLine();

        System.out.print("Enter Student Date of Birth (DOB): ");
        String dob = sc.nextLine();

        System.out.print("Enter Student ContactNo: ");
        int ContactNo = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        System.out.print("Enter Student BloodGroup: ");
        String BloodGroup = sc.nextLine();

        System.out.print("Enter Student Year of Study: ");
        int year = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        StudentModel student = new StudentModel(rollNo, name,gender, className, department, dob,
                ContactNo, BloodGroup, year);
        System.out.print("Enter Student Rank: ");
        int studentrank = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Student CGPA: ");
        Float cgpa=sc.nextFloat();
        sc.nextLine();
        System.out.print("Enter Student Attendance: ");
        int attendance = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Student Year of Passing: ");
        int passYear = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Student Arrear: ");
        int arrear = sc.nextInt();
        sc.nextLine();
        
        StudentAcademic academic=new StudentAcademic(rollNo,studentrank, cgpa,attendance, passYear, arrear);
        
        studentService.addStudent(student);
        academicDao.addAcademic(academic);
        System.out.println("Student added successfully.");
        
    }

    private static void viewStudents(StudentDao studentDao) {
        ArrayList<StudentModel> students = studentDao.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
        	 System.out.println("+------+----------------------+--------------+----------------------+------------+---------------------+");
             System.out.printf("| %-4s | %-10s | %-10s | %-10s | %-10s | %-10s | %-4s | %-10s | %-4s |%n", "ID", "Name", "Class","Gender", "Department", "DOB", "ContactNo", "BloodGroup", "Year of Study");
             
             for (StudentModel student : students) {
            	 System.out.println("+-------------------------------------------------------------------------------------------------------");
                 System.out.printf("| %-4d | %-10s | %-10s | %-10s | %-10s | %-10s | %-4d | %-10s | %-4s|%n",
                         student.getRollNo(), student.getName(),student.getGender(), student.getClassName(), student.getDepartment(),
                         student.getDob(), student.getContactNo(), student.getBloodGroup(), student.getYear());
             }
             System.out.println("+------+----------------------+--------------+----------------------+-------------+---------------------+");
            }
        }
    

    private static void updateStudent(Scanner sc, StudentDetails studentService) {
        System.out.print("Enter Student ID to update: ");
        int rollNo = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        StudentModel existingStudent = studentService.getStudentById(rollNo);
        if (existingStudent == null) {
            System.out.println("Student with ID " + rollNo + " not found.");
            return;
        }

        System.out.print("Enter new Student Name (Leave empty to keep current): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) {
            existingStudent.setName(name);
        }
        System.out.print("Enter new Student Gender (Leave empty to keep current): ");
        String gender = sc.nextLine();
        if (!gender.isEmpty()) {
            existingStudent.setName(gender);
        }

        System.out.print("Enter new Student Class (Leave empty to keep current): ");
        String className = sc.nextLine();
        if (!className.isEmpty()) {
            existingStudent.setClassName(className);
        }

        System.out.print("Enter new Student Department (Leave empty to keep current): ");
        String department = sc.nextLine();
        if (!department.isEmpty()) {
            existingStudent.setDepartment(department);
        }

        System.out.print("Enter new Student Date of Birth (DOB) (Leave empty to keep current): ");
        String dob = sc.nextLine();
        if (!dob.isEmpty()) {
            existingStudent.setDob(dob);
        }

        System.out.print("Enter new Student ContactNo (Enter -1 to keep current): ");
        int ContactNo = sc.nextInt();
        sc.nextLine(); // Consume the newline character
        if (ContactNo != -1) {
            existingStudent.setContactNo(ContactNo);
        }

        System.out.print("Enter new Student BloodGroup (Leave empty to keep current): ");
        String BloodGroup = sc.nextLine();
        if (!BloodGroup.isEmpty()) {
            existingStudent.setBloodGroup(BloodGroup);
        }

        System.out.print("Enter new Student Year of Study (Enter -1 to keep current): ");
        int year = sc.nextInt();
        sc.nextLine(); // Consume the newline character
        if (year != -1) {
            existingStudent.setYear(year);
        }

        studentService.updateStudent(existingStudent);
        System.out.println("Student with ID " + rollNo + " updated successfully.");
    }

    
}
