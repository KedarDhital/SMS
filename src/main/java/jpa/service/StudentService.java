package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.util.JpaUtil;
import org.hibernate.HibernateException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements StudentDAO {
    private final EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
    private EntityManager em = emf.createEntityManager();

    @Override
    public List<Student> getAllStudents() {
        TypedQuery<Student> query = em.createNamedQuery("getAllStudents", Student.class);
        List<Student> students = query.getResultList();
        return students;
    }

    @Override
    public Student getStudentByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        return em.find(Student.class, email);

    }

    @Override
    public boolean validateStudent(String sEmail, String sPassword) {
        Student student = null;
        try {
            student = em.find(Student.class, sEmail);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        if (student != null && student.getsPass().equals(sPassword)) {
            return true;
        }
        return false;
    }


    @Override
    public void registerStudentToCourse(String sEmail, int cId) {

        try {
            em.getTransaction().begin();

            Course course = new Course();
            Student student = new Student();

            List<Course> studentCourse = new ArrayList<Course>();

            StudentService getStudentService = new StudentService();

            student = getStudentService.getStudentByEmail(sEmail);

            studentCourse = getStudentCourses(sEmail);

            course = em.find(Course.class, cId);

            studentCourse.add(course);

            Student finalStudent = new Student(student.getsEmail(), student.getsName(), student.getsPass(), studentCourse);

            em.merge(finalStudent);

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("problem adding student to course");
        }
    }


    @Override
    public List<Course> getStudentCourses(String sEmail) {
        Student student = em.find(Student.class, sEmail);
        List<Course> stuCour = student.getsCourse();
        return stuCour;

    }
}