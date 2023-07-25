package Task_3;

import java.sql.SQLException;
import java.util.Scanner;

import Task_3.model.Student;
import Task_3.service.DatabaseService;

public class StudentManagementSystem {

    public static void main(String[] args) throws SQLException {

        DatabaseService databaseService = new DatabaseService();

        Scanner scanner = new Scanner(System.in);

        Student student = new Student();

        boolean isRunning = true;
        while (isRunning) {

            System.out.println("Enter your choice from below options");
            System.out.println("1. Add new Student");
            System.out.println("2. Update a Student");
            System.out.println("3. Delete a Student");
            System.out.println("4. Show a Student");
            System.out.println("5. Show all Student");
            System.out.println("8. Exit");

            System.out.println("Enter choice : ");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    System.out.println("Enter Student Roll No : ");
                    student.setRoll_no(scanner.nextInt());
                    System.out.println("Enter Student Name : ");
                    student.setName(scanner.next());
                    System.out.println("Enter Student Grade : ");
                    student.setGrade(scanner.next());

                    if (databaseService.addStudent(student)) {
                        System.out.println("Record added successfully !");
                    } else {
                        System.out.println("Insert failed");
                    }
                    break;

                case 2:

                    System.out.println("Enter Roll no of Student which you want to update : ");
                    int roll_no = scanner.nextInt();

                    System.out.println("Enter Student Name : ");
                    student.setName(scanner.next());
                    System.out.println("Enter Student Roll No : ");
                    student.setRoll_no(scanner.nextInt());
                    System.out.println("Enter Student Grade : ");
                    student.setGrade(scanner.next());

                    if (databaseService.updateStudent(roll_no, student)) {
                        System.out.println("Record updated successfully !");
                    } else {
                        System.out.println("Update failed");
                    }
                    break;

                case 3:
                    System.out.println("Enter roll no of student which you want to delete :");
                    roll_no = scanner.nextInt();

                    if (databaseService.deleteAStudent(roll_no)) {
                        System.out.println("Record deleted successfully !");
                    } else {
                        System.out.println("Delete failed");
                    }
                    break;

                case 4:
                    System.out.println("Enter roll no of student you want to see : ");
                    roll_no = scanner.nextInt();
                    student = databaseService.getAStudent(roll_no);

                    System.out.println("Name: " + student.getName());
                    System.out.println("Roll No: " + student.getRoll_no());
                    System.out.println("Grade: " + student.getGrade());
                    break;
                case 5:
                    int size = databaseService.resultsetSize();
                    int start = 1;
                    while (start <= size) {
                        student = databaseService.Select(start, size);
                        System.out.println(student.getName());
                        start++;
                    }
                    break;
                case 8:
                    isRunning = false;
                    break;

                default:
                    break;
            }
        }
        scanner.close();
    }

}
