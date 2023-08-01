package utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionManager {
 public static Connection getConnection() throws ClassNotFoundException {
	      String Driver = "com.mysql.cj.jdbc.Driver";
	      String URL = "jdbc:mysql://localhost:3306/studentinfo";
	      String USERNAME = "root";
	      String PASSWORD = "root";
	      Class.forName(Driver);
	      try {
	    	  Connection con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	    	  if(con!=null) {
	    		  return con;
	    	  }
	      }catch(SQLException e) {
	    	  System.out.println("unable to establish");
	    	  e.printStackTrace();
	      }
		return null;
	}

}