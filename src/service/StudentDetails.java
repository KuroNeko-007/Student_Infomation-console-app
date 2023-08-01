package service;

import java.util.ArrayList;
import dao.StudentDao;
import model.StudentModel;

public class StudentDetails {
    private final StudentDao studentDao = new StudentDao();

    // Add a new student
    public void addStudent(StudentModel student) {
        studentDao.addStudent(student);
    }

    // Get all students
    public ArrayList<StudentModel> getAllStudents() {
         return studentDao.getAllStudents();
    }

    // Get student by ID
    public StudentModel getStudentById(int studentId) {
        return studentDao.getStudentById(studentId);
    }
    public ArrayList<StudentModel> getbydept(String dept){
    	return studentDao.getStudentsByCourse(dept);
    }
    // Update student
    public void updateStudent(StudentModel student) {
        studentDao.updateStudent(student);
    }

    // Delete student by ID
    public void deleteStudent(int studentId) {
        studentDao.deleteStudent(studentId);
    }
}
