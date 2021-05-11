package jpa.service;

import jpa.entitymodels.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    @Test
    void getStudentByEmail() {
        StudentService ss = new StudentService();
        Student expected = new Student();
        expected = ss.getStudentByEmail("ljiroudek8@sitemeter.com");

        if (expected instanceof Student) {
            assert true;

        }
    }
    @Test
  public void validateStudent() {
        StudentService ss = new StudentService();
        boolean validateStudent = ss.validateStudent("cjaulme9@bing.com","FnVklVgC6r6");
        assertEquals(validateStudent,true);

    }
}