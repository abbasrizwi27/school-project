package com.example.School;

import com.example.School.Model.ResponseOutput;
import com.example.School.Model.Student;
import com.example.School.Service.SchoolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
public class SchoolTest {

    String nameOne = "Abbas";
    String nameTwo = "Karan";
    double mathMarks = 40;
    double mathMarks2 = 50;
    double scienceMarks = 60;
    int classFive = 5;

    @Autowired
    public SchoolService schoolService;

    @Test
    public void addStudent() {

        Student student = new Student();
        student.setStudentName(nameOne);
        student.setClassno(classFive);
        student.setMathsMarks(mathMarks);
        student.setScienceMarks(scienceMarks);
        ResponseOutput responseOutput = schoolService.addStudent(student);
        Assert.notNull(responseOutput);
    }

    @Test
    public void addStudents() {

        List<Student> studentList = new ArrayList<>();
        Student students = new Student();
        students.setStudentName(nameOne);
        students.setClassno(classFive);
        students.setScienceMarks(scienceMarks);
        students.setMathsMarks(mathMarks2);
        studentList.add(students);
        students = new Student();
        students.setStudentName(nameTwo);
        students.setClassno(classFive);
        students.setScienceMarks(scienceMarks);
        students.setMathsMarks(mathMarks);
        studentList.add(students);
        ResponseOutput responseOutput = schoolService.addStudents(studentList);
        Assert.notNull(responseOutput.isSuccess() == true);
//        Assert.isTrue(responseOutput.getMessage().equals("Succe"));
    }

    @Test
    public void StudentsOfClass() {

        List<Student> studentList = new ArrayList<>();
        Student students = new Student();
        students.setStudentName(nameOne);
        students.setClassno(classFive);
        students.setScienceMarks(scienceMarks);
        students.setMathsMarks(mathMarks2);
        studentList.add(students);
        students = new Student();
        students.setStudentName(nameTwo);
        students.setClassno(classFive);
        students.setScienceMarks(scienceMarks);
        students.setMathsMarks(mathMarks);
        studentList.add(students);
        ResponseOutput responseOutput = schoolService.addStudents(studentList);
        List<Student> student = schoolService.getStudentsOfClass(classFive);
        for (int i = 0; i < student.size(); i++) {
            Student student1 = student.get(i);
            Assert.notNull(student1);
        }
        Assert.isTrue(student.size() == studentList.size());
    }

    @Test
    public void TopperOfClass() {

        List<Student> studentList = new ArrayList<>();
        Student students = new Student();
        students.setStudentName(nameOne);
        students.setClassno(classFive);
        students.setScienceMarks(scienceMarks);
        students.setMathsMarks(mathMarks2);
        studentList.add(students);
        students = new Student();
        students.setStudentName(nameTwo);
        students.setClassno(classFive);
        students.setScienceMarks(scienceMarks);
        students.setMathsMarks(mathMarks);
        studentList.add(students);
        ResponseOutput responseOutput = schoolService.addStudents(studentList);
        Student student = schoolService.getTopperOfClass(classFive);
        Assert.notNull(student);
    }

    @Test
    public void AvgMarksOfMaths() {
        double mathMarks =0;
        List<Student> studentList = new ArrayList<>();
        Student students = new Student();
        students.setStudentName(nameOne);
        students.setClassno(classFive);
        students.setScienceMarks(scienceMarks);
        students.setMathsMarks(mathMarks2);
        mathMarks = mathMarks +students.getMathsMarks();
        studentList.add(students);
        students = new Student();
        students.setStudentName(nameTwo);
        students.setClassno(classFive);
        students.setScienceMarks(scienceMarks);
        students.setMathsMarks(mathMarks);
        mathMarks = mathMarks +students.getMathsMarks();
        studentList.add(students);
        ResponseOutput responseOutput = schoolService.addStudents(studentList);
        double avgMath = schoolService.getAvgMathMarksOfClass(classFive);
        Assert.notNull(avgMath);
        Assert.isTrue((mathMarks/studentList.size()) == avgMath);
    }

    @Test
    public void AvgMArksOfScience() {
        List<Student> studentList = new ArrayList<>();
        Student students = new Student();
        students.setStudentName(nameOne);
        students.setClassno(classFive);
        students.setScienceMarks(scienceMarks);
        students.setMathsMarks(mathMarks2);
        studentList.add(students);
        students = new Student();
        students.setStudentName(nameTwo);
        students.setClassno(classFive);
        students.setScienceMarks(scienceMarks);
        students.setMathsMarks(mathMarks);
        studentList.add(students);
        ResponseOutput responseOutput = schoolService.addStudents(studentList);

        double avgScience = schoolService.getAvgScienceMarksOfClass(classFive);
        System.out.print(avgScience);
        Assert.notNull(avgScience);
    }

    @Test
    public void NoOfStudentByClassno() {
        List<Student> studentList = new ArrayList<>();
        Student students = new Student();
        students.setStudentName(nameOne);
        students.setClassno(classFive);
        students.setScienceMarks(scienceMarks);
        students.setMathsMarks(mathMarks2);
        studentList.add(students);
        students = new Student();
        students.setStudentName(nameTwo);
        students.setClassno(classFive);
        students.setScienceMarks(scienceMarks);
        students.setMathsMarks(mathMarks);
        studentList.add(students);
        ResponseOutput responseOutput = schoolService.addStudents(studentList);
        int noOfStudent = schoolService.getNoOfStudentOfClass(classFive);
        System.out.println(noOfStudent);
        Assert.notNull(noOfStudent);
        Assert.isTrue(studentList.size() == noOfStudent);
    }


}
