package jpa.entitymodels;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


@Entity
@Table(name = "Course")

public class Course  {

    @Id
    @Column(name = "id")
    private int cId;

    @Column(name = "name")
    private String cName;

    @Column(name = "instructor")
    private String cInstructorName;


    // default constructor
    public Course() {
    }

    // args constructor
    public Course(int cId, String cName, String cInstructorName) {
        this.cId = cId;
        this.cName = cName;
        this.cInstructorName = cInstructorName;
    }
 // getters and setters for all class member
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcInstructorName() {
        return cInstructorName;
    }

    public void setcInstructorName(String cInstructorName) {
        this.cInstructorName = cInstructorName;
    }

    // overriding to string method for better display

    @Override
    public String toString() {
        return String.format("%-10s%-40s%-20s", cId,cName,cInstructorName);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return cId == course.cId && cName.equals(course.cName) && cInstructorName.equals(course.cInstructorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cId, cName, cInstructorName);
    }
}
