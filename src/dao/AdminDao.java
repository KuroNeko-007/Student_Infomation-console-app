package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.AdminModel;
import utility.ConnectionManager;

public class AdminDao extends GetConnection implements AdminInterface{

	Connection dbConn = null;
	@Override
	public Connection getDbConnection() throws ClassNotFoundException {
		Connection connection = ConnectionManager.getConnection();
		return connection;
	}
	
    public AdminModel getAdminByUsername(String name) throws ClassNotFoundException {
        AdminModel admin = null;
        try {
            dbConn = getDbConnection();
            String SELECT = "SELECT * FROM admin WHERE name=?";
            PreparedStatement ps = dbConn.prepareStatement(SELECT);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name1 = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                admin = new AdminModel(id, name1, email, password);
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
        return admin;
    }
}
