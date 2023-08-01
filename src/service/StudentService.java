package service;

import java.util.ArrayList;

import dao.StudentLoginDao;
import model.StudentLoginModel;
import model.StudentModel;

public class StudentService {
    private StudentLoginDao StudentLoginDao = new StudentLoginDao();

    public boolean authenticateStudent(String name, String password) throws ClassNotFoundException {
    	StudentLoginModel student = StudentLoginDao.getStudentByName(name);
        if (student != null && student.getPassword().equals(password)) {
            return true; // Authentication successful
        }
        return false;
    }
    
}
