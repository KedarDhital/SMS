package jpa.entitymodels;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Student")

public class Student {

    @Id
    @Column(name ="email")
    private String sEmail;

    @Column(name = "name")
    private String sName;

    @Column(name = "password")
    private String sPass;

    // one to many relationship because one instance of student class is related to many instance of course class.
    @OneToMany(targetEntity = Course.class)
    private List<Course> sCourse;

    // default constructor
    public Student() {
    }
    // constructor without sCourse
    public Student(String sEmail, String sName, String sPass) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
    }
 // constructor with sCourse
    public Student(String sEmail, String sName, String sPass, List<Course> sCourse) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.sCourse = sCourse;

    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    public List<Course> getsCourse() {
        return sCourse;
    }

    public void setsCourse(List<Course> sCourse) {
        this.sCourse = sCourse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return sEmail.equals(student.sEmail) && sName.equals(student.sName) && sPass.equals(student.sPass) && sCourse.equals(student.sCourse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sEmail, sName, sPass, sCourse);
    }

    @Override
    public String toString() {
        return   "Name='" + sName + '\'' +
                ", Email='" + sEmail + '\'' +
                ", Password='" + sPass + '\'' +
                '}';


    }
}
