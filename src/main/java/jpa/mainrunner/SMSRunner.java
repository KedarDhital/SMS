package jpa.mainrunner;/*
 * Filename: SMSRunner.java
 * Author: Stefanski
 * 02/25/2020
 */

import static java.lang.System.out;

import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;


public class SMSRunner {

    private final Scanner sin;

    private final CourseService courseService;
    private final StudentService studentService;
    private Student currentStudent;

    public SMSRunner() {
        sin = new Scanner(System.in);
        courseService = new CourseService();
        studentService = new StudentService();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        SMSRunner sms = new SMSRunner();
        sms.run();
    }

    private void run() {
        // Login or quit
        int num = menu1();
        while (num != 2) {
            if (studentLogin()) {
                registerMenu();
            }
            num = menu1();
        }
        out.println("Goodbye!");
    }

    private int menu1() {
        out.print("\n1. Student Login\n2. Quit Application\nPlease Enter Selection: ");

        int i;
        while(true) {
            try {
                i = sin.nextInt();
                break;
            } catch(Exception ignored) {
                sin.nextLine();
                out.println("That's not a number. Try again.");
                out.print("Please Enter Selection: ");
            }
        }
        return i;
    }

    private boolean studentLogin() {
        boolean retValue = false;
        out.print("Enter your email address: ");
        String email = sin.next();
        out.print("Enter your password: ");
        String password = sin.next();

        currentStudent = studentService.getStudentByEmail(email);

        if (currentStudent != null && currentStudent.getsPass().equals(password)) {
            List<Course> courses = studentService.getStudentCourses(email);
            out.println("My Classes");
            out.printf("%-10S%-40S%-20S\n", "ID", "Course", "Instructor");
            for (Course course : courses) {
                out.println(course);
            }
            retValue = true;
        } else {
            out.println("User Validation failed. GoodBye!");
        }
        return retValue;
    }

    private void registerMenu() {
        int num;
        while(true) {
            out.print("\n1. Register a class\n2. Logout\nPlease Enter Selection: ");
            num = sin.nextInt();
            if (num == 1) {
                List<Course> allCourses = courseService.getAllCourses();
                List<Course> studentCourses = studentService.getStudentCourses(currentStudent.getsEmail());
                allCourses.removeAll(studentCourses);
                out.printf("%-10S%-40S%-20S\n", "ID", "Course", "Instructor");
                for (Course course : allCourses) {
                    out.println(course);
                }
                out.println();
                out.print("Enter Course Number: ");
                int number = sin.nextInt();

                studentService.registerStudentToCourse(currentStudent.getsEmail(), number);
                Student temp = studentService.getStudentByEmail(currentStudent.getsEmail());

                List<Course> sCourses = studentService.getStudentCourses(temp.getsEmail());


                out.println("My Classes: ");
                out.printf("%-10S%-40S%-20S\n", "ID", "Course", "Instructor");
                for (Course course : sCourses) {
                    out.println(course);
                }
            } else if(num == 2) {
                out.println("Goodbye!");
                break;
            }
        }
    }
}
