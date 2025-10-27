package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, rollNumber, course, marks) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getRollNumber());
            ps.setString(3, student.getCourse());
            ps.setDouble(4, student.getMarks());
            ps.executeUpdate();
            System.out.println("✅ Student added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentByRollNumber(String rollNumber) {
        Student student = null;
        String sql = "SELECT * FROM students WHERE rollNumber = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, rollNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                student = new Student(
                        rs.getString("name"),
                        rs.getString("rollNumber"),
                        rs.getString("course"),
                        rs.getDouble("marks")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getString("name"),
                        rs.getString("rollNumber"),
                        rs.getString("course"),
                        rs.getDouble("marks")
                );
                students.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name=?, course=?, marks=? WHERE rollNumber=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getCourse());
            ps.setDouble(3, student.getMarks());
            ps.setString(4, student.getRollNumber());

            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Student updated successfully!");
            else
                System.out.println("⚠️ Student not found!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(String rollNumber) {
        String sql = "DELETE FROM students WHERE rollNumber=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, rollNumber);
            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("🗑️ Student deleted successfully!");
            else
                System.out.println("⚠️ Student not found!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}