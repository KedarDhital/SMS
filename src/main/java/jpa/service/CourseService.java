package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.util.JpaUtil;
import org.hibernate.HibernateException;

import javax.persistence.*;
import java.util.List;

public class CourseService implements CourseDAO {
    final private EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

    @Override
    public List<Course> getAllCourses() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Course> query = em.createQuery("SELECT c from Course c", Course.class);

        return query.getResultList();

    }
}