package com.smsproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentManagerImpl implements StudentManager {

    private Connection connection = null;

    public void connectToDB(String url) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                "name TEXT, " +
                "age INTEGER, " +
                "grade REAL, " +
                "studentID TEXT PRIMARY KEY);";

        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to the database.");

            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);

        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public void disconnectFromDB() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Disconnected from the database.");
            } catch (SQLException e) {
                System.out.println("Error disconnecting: " + e.getMessage());
            }
        } else {
            System.out.println("No active connection to disconnect.");
        }
    }

    @Override
    public void addStudent(Student student) {
        String query = "INSERT INTO students (name, age, grade, studentID) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setDouble(3, student.getGrade());
            preparedStatement.setString(4, student.getStudentID());

            preparedStatement.executeUpdate();
            System.out.println("Student added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding student to database: " + e.getMessage());
        }
    }

    @Override
    public void removeStudent(String studentID) {
        String query = "DELETE FROM students WHERE studentID = (?)";

        if (studentID == null || studentID.trim().isEmpty()) {
            System.out.println("Error: Student ID cannot be null or empty.");
            return;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentID);
            
            preparedStatement.executeUpdate();
            System.out.println("Student removed successfully.");

        } catch (SQLException e) {
            System.out.println("Error removing student from database: " + e.getMessage());
        }


    }

    @Override
    public void updateStudent(String studentID, String newName, int newAge, double newGrade) {
    
    String query = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        
        preparedStatement.setString(1, newName);
        preparedStatement.setInt(2, newAge);
        preparedStatement.setDouble(3, newGrade);
        preparedStatement.setString(4, studentID);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Student with ID " + studentID + " updated successfully.");
        } else {
            System.out.println("No student found with ID " + studentID);
        }

    } catch (SQLException e) {
        System.out.println("Error updating student in the database: " + e.getMessage());
    }
    }

    @Override
    public ArrayList<Student> displayAllStudents() {
        ArrayList<Student> students = new ArrayList<>();

        String query = "SELECT * FROM students"; 

        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {

            
            while (resultSet.next()) {
                
                Student student = new Student(
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getDouble("grade"),
                    resultSet.getString("studentID")
                );

                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving students from database: " + e.getMessage());
        }

        return students;
    }

    @Override
    public double calculateAverageGrade() {
        String query = "SELECT * FROM students"; 
        double gradeSum = 0;
        int studentCount = 0;

        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                gradeSum += resultSet.getDouble("grade");
                studentCount++;
            }

            if (studentCount > 0) {
                return gradeSum / studentCount;
            } else {
                System.out.println("No students found.");
                return 0;
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving students from database: " + e.getMessage());
            return 0;
        }
    }
}
