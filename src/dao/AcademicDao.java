package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.StudentAcademic;
import model.StudentModel;
import model.StudentAcademic;
import utility.ConnectionManager;

public class AcademicDao extends GetConnection{

	Connection dbConn = null;
	@Override
	public Connection getDbConnection() throws ClassNotFoundException {
		Connection connection = ConnectionManager.getConnection();
		return connection;
	}
	
	public ArrayList<StudentAcademic> getAllAcademic() {
        ArrayList<StudentAcademic> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getDbConnection();
            String query = "SELECT * FROM academic ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	int rollNo = resultSet.getInt("rollNo");
                int studentrank = resultSet.getInt("studentrank");
                Float cgpa = resultSet.getFloat("cgpa");
                int attendance = resultSet.getInt("attendance");
                int passYear = resultSet.getInt("passYear");
                int arrear = resultSet.getInt("arrear");

                StudentAcademic student = new StudentAcademic(rollNo,studentrank, cgpa,attendance, passYear, arrear);
                students.add(student);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return students;
    }
	public StudentAcademic getStudentbyId(int id) {
        StudentAcademic student;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getDbConnection();
            String query = "SELECT * FROM academic where rollno= ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	int rollNo = resultSet.getInt("rollNo");
                int studentrank = resultSet.getInt("studentrank");
                Float cgpa = resultSet.getFloat("cgpa");
                int attendance = resultSet.getInt("attendance");
                int passYear = resultSet.getInt("passYear");
                int arrear = resultSet.getInt("arrear");

                return new StudentAcademic(rollNo,studentrank, cgpa,attendance, passYear, arrear);

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
	
	public void addAcademic(StudentAcademic academic) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getDbConnection();
            String query = "INSERT INTO academic(rollNo,studentrank,cgpa,attendance,passYear, arrear) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, academic.getRollNo());
            preparedStatement.setInt(2, academic.getStudentrank());
            preparedStatement.setFloat(3, academic.getCgpa());
            preparedStatement.setInt(4, academic.getAttendance());
            preparedStatement.setInt(5, academic.getPassYear());
            preparedStatement.setInt(6, academic.getArrear());


            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
