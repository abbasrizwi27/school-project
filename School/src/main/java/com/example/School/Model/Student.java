package com.example.School.Model;

import javax.persistence.*;

@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "classno")
    private int classno;

    @Column(name = "studentName")
    private String studentName;

    @Column(name = "mathsMarks")
    private double mathsMarks;

    @Column(name = "scienceMarks")
    private double scienceMarks;



    @Column(name="totalMarks")
    private double totalMarks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getClassno() {
        return classno;
    }

    public void setClassno(int classno) {
        this.classno = classno;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getMathsMarks() {
        return mathsMarks;
    }

    public void setMathsMarks(double mathsMarks) {
        this.mathsMarks = mathsMarks;
    }

    public double getScienceMarks() {
        return scienceMarks;
    }
    public double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public void setScienceMarks(double scienceMarks) {
        this.scienceMarks = scienceMarks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", classno=" + classno +
                ", studentName='" + studentName + '\'' +
                ", mathsMarks=" + mathsMarks +
                ", scienceMarks=" + scienceMarks +
                '}';
    }
}
