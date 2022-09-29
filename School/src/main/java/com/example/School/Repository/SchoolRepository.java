package com.example.School.Repository;

import com.example.School.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<Student,Long> {
    List<Student> findByClassno(int classno);
    Student findByClassnoAndStudentName(int classno, String studentname);
    Student findTopByClassnoOrderByTotalMarksDesc(int classno);

}
