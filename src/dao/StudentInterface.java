package dao;

import java.sql.SQLException;
import model.StudentLoginModel;

public interface StudentInterface {

	StudentLoginModel getStudentByName(String name) throws ClassNotFoundException, SQLException;
}
