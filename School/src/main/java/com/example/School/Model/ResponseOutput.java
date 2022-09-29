package com.example.School.Model;

import java.util.ArrayList;
import java.util.List;


public class ResponseOutput {
    String message;
    String errorMessage;
    private boolean success;
    private List<Student> studentList;

    public ResponseOutput() {
        this.success = true;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.success = false;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(Student student) {
        if (this.studentList == null) {
            this.studentList = new ArrayList<>();
        }
        this.studentList.add(student);
    }

    public void setStudentList(List<Student> student) {
        this.success = true;
        this.studentList = student;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
