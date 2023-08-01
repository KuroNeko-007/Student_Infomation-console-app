package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.AdminModel;
import model.StudentLoginModel;
import utility.ConnectionManager;

public class StudentLoginDao extends GetConnection implements StudentInterface{

	Connection dbConn = null;
	@Override
	public Connection getDbConnection() throws ClassNotFoundException {
		Connection connection = ConnectionManager.getConnection();
		return connection;
	}
	
    public StudentLoginModel getStudentByName(String name) throws ClassNotFoundException {
    	StudentLoginModel student = null;
        try {
            dbConn = getDbConnection();
            String SELECT = "SELECT * FROM studentlogin WHERE name=?";
            PreparedStatement ps = dbConn.prepareStatement(SELECT);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name1 = rs.getString("name");
                String password = rs.getString("password");
                student = new StudentLoginModel(name1, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dbConn != null) {
                    dbConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }
}
