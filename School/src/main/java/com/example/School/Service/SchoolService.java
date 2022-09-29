package com.example.School.Service;

import com.example.School.Model.ResponseOutput;
import com.example.School.Model.Student;
import com.example.School.Repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolService {
    @Autowired
    SchoolRepository schoolRepository;

    /**
     * This method is validating the student bean attributes and if successfully validated then persisting in db.
     * @param student
     * @return
     */
    public ResponseOutput addStudent(Student student) {
        try {
            ResponseOutput responseOutput = new ResponseOutput();
            responseOutput = validate(student, responseOutput);
            if (!responseOutput.isSuccess()) {
                return responseOutput;
            }
            student.setTotalMarks(student.getMathsMarks() + student.getScienceMarks());
            Student studentnew = schoolRepository.save(student);
            responseOutput.setStudentList(studentnew);
            responseOutput.setMessage("SuccessFully Added Student");
            return responseOutput;
        } catch (Exception e) {
            ResponseOutput responseOutput = new ResponseOutput();
//            System.out.println(e.getMessage());
            responseOutput.setErrorMessage("Exception Caught");
            return responseOutput;
        }
    }

    /**
     * This method is validating and adding a list of students in db.
     * @param schoolList
     * @return
     */
    public ResponseOutput addStudents(List<Student> schoolList) {
        ResponseOutput responseOutput = new ResponseOutput();
        try {
            for (Student student : schoolList) {
                responseOutput = validate(student, responseOutput);
                if (!responseOutput.isSuccess()) {
                    return responseOutput;
                }
            }
            for (int i = 0; i < schoolList.size(); i++) {
                Student student = schoolList.get(i);
                student.setTotalMarks(student.getMathsMarks() + student.getScienceMarks());
            }
            List<Student> studentList = schoolRepository.saveAll(schoolList);
            responseOutput.setStudentList(studentList);
            return responseOutput;

        }catch (Exception e){
            responseOutput.setErrorMessage("Exception Caught in addStudents");
            return responseOutput;
        }
    }

    /**
     * This method will return  all students of a class no passed to the method.
     * @param classno
     * @return
     */

    public List<Student> getStudentsOfClass(int classno) {
        try {
            return schoolRepository.findByClassno(classno);
        } catch (Exception e) {
            System.out.println("Exception caught in getStudentOf Class method");
            return new ArrayList<>();
        }
    }

    /**
     * This method will return the topper of a class based on the total marks.
     * @param classno
     * @return
     */
    public Student getTopperOfClass(int classno) {
        try {
            List<Student> list = schoolRepository.findByClassno(classno);
            double max = -1000;
            Student topper = null;
            for (int i = 0; i < list.size(); i++) {
                Student school = list.get(i);

                double marks = school.getMathsMarks() + school.getScienceMarks();
                if (max < marks) {
                    max = marks;
                    topper = school;
                }


            }
            return topper;
        }catch (Exception e){
            System.out.println("Exception Caught In getTopperOfClass method");
            return new Student();
        }
    }

    /**
     * This method will return the avg maths marks of students of a class.
     * @param classno
     * @return
     */
    public double getAvgMathMarksOfClass(int classno) {
        try {


            List<Student> list = schoolRepository.findByClassno(classno);

            double sum = 0;
            for (int i = 0; i < list.size(); i++) {
                Student student = list.get(i);
                sum = sum + student.getMathsMarks();

            }
            double avg = sum / list.size();
            return avg;
        }catch (Exception e){
           System.out.println("Exception Caught in getAvgMathMarksOfClass method");
            return 0;
        }
    }

    /**
     * This method will return the avg science marks of students of a class.
     * @param classno
     * @return
     */

    public double getAvgScienceMarksOfClass(int classno) {
        try {
            List<Student> list = schoolRepository.findByClassno(classno);

            double sum = 0;
            for (int i = 0; i < list.size(); i++) {
                Student student = list.get(i);
                sum = sum + student.getScienceMarks();

            }
            double avg = sum / list.size();
            return avg;
        }catch (Exception e){
            System.out.println("Exception Caught in getAvgScienceMarksOfClass method");
            return 0;
        }
    }

    /**
     * This method will return the no of students of a class;
     * @param classno
     * @return
     */
    public int getNoOfStudentOfClass(int classno) {
        try {

            List<Student> studentList = schoolRepository.findByClassno(classno);
            return studentList.size();
        }catch (Exception e){
            System.out.println("Exception Caught in getNoOfStudentOfClass method");
            return 0;
        }

    }

    /**
     * This method will return the student record by name of a class.
     * @param classno
     * @param studentname
     * @return
     */
    public ResponseOutput getStudentOfClassByName(int classno, String studentname) {
        ResponseOutput responseOutput = new ResponseOutput();
        try {
           Student student=schoolRepository.findByClassnoAndStudentName(classno, studentname);
            responseOutput.setStudentList(student);
            return responseOutput;
        }catch (Exception e){
            responseOutput.setErrorMessage("Exception Caught");
            return responseOutput;
        }
    }

    /**
     * This method will return topper of class by ordering the data in desc order.
     * @param classno
     * @return
     */
    public Student getTopper(int classno) {
        try {
            return schoolRepository.findTopByClassnoOrderByTotalMarksDesc(classno);
        }catch (Exception e){
            System.out.println("Exception caught in getTopper Method");
            return null;
        }
    }

    /**
     * This method will return all students from db.
     * @return
     */
    public List<Student> getAllStudent() {
        try {
            return schoolRepository.findAll();
        }catch (Exception e){
            System.out.println("Exception Caught In getAllStudent");
            return null;
        }
    }

    public Student getStudentById(long id) {
        try {
            return schoolRepository.findById(id).get();
        }catch (Exception e){
            System.out.println("Exception Caught In getStudentById");
            return null;
        }
    }

    /**
     * This method will update the existing student record.
     * @param student
     * @return
     */
    @Transactional
    public ResponseOutput updateStudent(Student student) {
        ResponseOutput responseOutput = new ResponseOutput();

        try {
            responseOutput = validate(student, responseOutput);
            if (!responseOutput.isSuccess()) {
                return responseOutput;
            }
            Student recordFromDB = schoolRepository.getById(student.getId());
            recordFromDB.setStudentName(student.getStudentName());
            recordFromDB.setClassno(student.getClassno());
            recordFromDB.setScienceMarks(student.getScienceMarks());
            recordFromDB.setMathsMarks(student.getMathsMarks());
            recordFromDB.setTotalMarks(student.getMathsMarks() + student.getScienceMarks());
            responseOutput.setStudentList(recordFromDB);
            responseOutput.setMessage("Updated SuccessFully");
            return responseOutput;
        }catch (Exception e){
            responseOutput.setErrorMessage("Exception Caught In UpdateStudent method");
            return responseOutput;
        }
    }

    /**
     * This method will validate the student passed to it.
     * @param student
     * @param responseOutput
     * @return
     */
    public ResponseOutput validate(Student student, ResponseOutput responseOutput) {
        if (student.getScienceMarks() < 0 || student.getScienceMarks() > 100) {
            responseOutput.setErrorMessage("Science Marks is out of range(1-100)");
            return responseOutput;
        }
        if (student.getMathsMarks() < 0 || student.getMathsMarks() > 100) {
            responseOutput.setErrorMessage("Maths Marks is out of range(1-100)");
            return responseOutput;
        }
        if (student.getClassno() < 1 || student.getClassno() > 12) {
            responseOutput.setErrorMessage("Class no is out of range(1-12)");
            return responseOutput;
        }
        return responseOutput;
    }
}
