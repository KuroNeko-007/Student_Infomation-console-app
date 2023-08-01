package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.StudentModel;
import utility.ConnectionManager;

public class StudentDao extends GetConnection{
	Connection connection = null;
	@Override
	public Connection getDbConnection() throws ClassNotFoundException {
		Connection connection = ConnectionManager.getConnection();
		return connection;
	}
	
    public void addStudent(StudentModel student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getDbConnection();
            String query = "INSERT INTO students(rollNo,name,gender,className,department, dob,contactNo,bloodGroup,year) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, student.getRollNo());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setString(4, student.getClassName());
            preparedStatement.setString(5, student.getDepartment());
            preparedStatement.setString(6, student.getDob());
            preparedStatement.setInt(7, student.getContactNo());
            preparedStatement.setString(8, student.getBloodGroup());
            preparedStatement.setInt(9, student.getYear());

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

    public ArrayList<StudentModel> getStudentsByCourse(String department) {
        ArrayList<StudentModel> academicDetails = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getDbConnection();
            String query = "SELECT * FROM students WHERE department = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, department);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int rollNo = resultSet.getInt("rollNo");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String className = resultSet.getString("className");
                String department1 = resultSet.getString("department");
                String dob = resultSet.getString("dob");
                int contactNo = resultSet.getInt("contactNo");
                String bloodGroup = resultSet.getString("bloodGroup");
                int year = resultSet.getInt("year");
                StudentModel academic = new StudentModel(rollNo, name, gender, className,
                		department1, dob, contactNo,bloodGroup,year);
                academicDetails.add(academic);
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

        return academicDetails;
    }
    public ArrayList<StudentModel> getAllStudents() {
        ArrayList<StudentModel> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getDbConnection();
            String query = "SELECT * FROM students";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int rollNo = resultSet.getInt("rollNo");
                String gender = resultSet.getString("gender");
                String name = resultSet.getString("name");
                String className = resultSet.getString("className");
                String department = resultSet.getString("department");
                String dob = resultSet.getString("dob");
                int contactNo = resultSet.getInt("contactNo");
                String bloodGroup = resultSet.getString("bloodGroup");
                int year = resultSet.getInt("year");

                StudentModel student = new StudentModel(rollNo, name,gender, className, department, dob,
                        contactNo, bloodGroup, year);
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

    public StudentModel getStudentById(int rollNo) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getDbConnection();
            String query = "SELECT * FROM students WHERE rollNo = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, rollNo);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String className = resultSet.getString("className");
                String department = resultSet.getString("department");
                String dob = resultSet.getString("dob");
                String bloodGroup = resultSet.getString("bloodGroup");
                int contactNo = resultSet.getInt("contactNo");
                int year = resultSet.getInt("year");

                return new StudentModel(rollNo, name, gender,className, department, dob,
                        contactNo,bloodGroup, year);
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

    public void updateStudent(StudentModel student) {
        
        PreparedStatement preparedStatement = null;

        try {
            connection = getDbConnection();
            String query = "UPDATE students SET name = ?,gender = ?, className = ?, department = ?, dob = ?, contactNo = ?,bloodGroup = ?, year = ? WHERE rollNo = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getGender());
            preparedStatement.setString(3, student.getClassName());
            preparedStatement.setString(4, student.getDepartment());
            preparedStatement.setString(5, student.getDob());
            preparedStatement.setFloat(6, student.getContactNo());
            preparedStatement.setString(7, student.getBloodGroup());
            preparedStatement.setInt(8, student.getYear());
            preparedStatement.setInt(9, student.getRollNo());

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

    public void deleteStudent(int rollNo) {
        PreparedStatement preparedStatement = null;

        try {
            connection = getDbConnection();
            String query = "DELETE FROM students WHERE rollNo = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, rollNo);

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
