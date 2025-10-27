package org.example;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAOImpl();
        int choice;

        do {
            System.out.println("\n===== Student Management Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Student by Roll Number");
            System.out.println("3. View All Students");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student by Roll Number");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter roll number: ");
                    String rollNumber = sc.nextLine();
                    System.out.print("Enter course: ");
                    String course = sc.nextLine();
                    System.out.print("Enter marks: ");
                    double marks = sc.nextDouble();

                    dao.addStudent(new Student(name, rollNumber, course, marks));
                    break;

                case 2:
                    System.out.print("Enter roll number: ");
                    String searchRoll = sc.nextLine();
                    Student s = dao.getStudentByRollNumber(searchRoll);
                    System.out.println(s != null ? s : "❌ Student not found");
                    break;

                case 3:
                    List<Student> list = dao.getAllStudent();
                    if (list.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        list.forEach(System.out::println);
                    }
                    break;

                case 4:
                    System.out.print("Enter roll number to update: ");
                    String updateRoll = sc.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new course: ");
                    String newCourse = sc.nextLine();
                    System.out.print("Enter new marks: ");
                    double newMarks = sc.nextDouble();

                    dao.updateStudent(new Student(newName, updateRoll, newCourse, newMarks));
                    break;

                case 5:
                    System.out.print("Enter roll number to delete: ");
                    String deleteRoll = sc.nextLine();
                    dao.deleteStudent(deleteRoll);
                    break;

                case 6:
                    System.out.println("👋 Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice, try again!");
            }
        } while (choice != 6);

        sc.close();
    }
}