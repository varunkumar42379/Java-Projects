package org.example;

import java.util.List;

public interface StudentDAO {
    void addStudent(Student student);
    Student getStudentByRollNumber(String rollNumber);
    List<Student> getAllStudent();
    void updateStudent(Student student);
    void deleteStudent(String rollNumber);
}