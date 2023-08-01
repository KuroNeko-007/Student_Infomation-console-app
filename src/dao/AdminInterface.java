package dao;

import java.sql.SQLException;
import model.AdminModel;
import model.StudentLoginModel;

public interface AdminInterface {

    AdminModel getAdminByUsername(String name) throws ClassNotFoundException, SQLException;


}
