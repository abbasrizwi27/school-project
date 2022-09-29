package com.example.School.Controller;

import com.example.School.Model.ResponseOutput;
import com.example.School.Model.Student;
import com.example.School.SchoolApplication;
import com.example.School.Service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    SchoolService schoolService;

    /**
     * This controller will show the home page
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    /**
     * This Controller will accept the student model and add it in the db
     * @param model
     * @param student
     * @return
     */
    @PostMapping(value = "/addStudent/form", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addStudent(Model model, Student student) {
        System.out.println(student);
        ResponseOutput responseOutput = schoolService.addStudent(student);
        return page(model,responseOutput);
    }

    /**
     * The controller will accept student data in json format.
     * @param student
     * @return
     */
    @PostMapping(value = "/addStudent/json")
    @ResponseBody
    public ResponseOutput addStudentjson(@RequestBody Student student) {
        System.out.println(student);
        return schoolService.addStudent(student);
    }

    /**
     * This method will accept a list of students in json format.
     * @param students
     * @return
     */

    @PostMapping("/addStudents")
    @ResponseBody
    public ResponseOutput addStudents(@RequestBody List<Student> students) {
        ResponseOutput responseOutput =  schoolService.addStudents(students);
        return responseOutput;
    }

    /**
     * This method will return the student record of a class.
     * @param classno
     * @return
     */
    @GetMapping("/getStudentsOfClass/{classno}")
    @ResponseBody
    public List<Student> getStudentOfClass(@PathVariable int classno) {
        return schoolService.getStudentsOfClass(classno);
    }

    @GetMapping("/getTopperOfClass/{classno}")
    @ResponseBody
    public Student getTopperOfClass(@PathVariable int classno) {
        return schoolService.getTopperOfClass(classno);
    }

    @GetMapping("/getAvgMathMarksOfClass/{classno}")
    public double getAvgMathMarksOfClass(@PathVariable int classno) {
        return schoolService.getAvgMathMarksOfClass(classno);
    }

    @GetMapping("/getAvgScienceMarksOfClass/{classno}")
    public double getAvgScienceMarksOfClass(@PathVariable int classno) {
        return schoolService.getAvgScienceMarksOfClass(classno);
    }

    @GetMapping("/getNoOfStudentOfClass/{classno}")
    public int getNoOfStudentOfClass(@PathVariable int classno) {
        return schoolService.getNoOfStudentOfClass(classno);
    }

    @GetMapping("/getStudentOfClassByName/{classno}/{studentname}")
    @ResponseBody
    public ResponseOutput getStudentOfClassByName(@PathVariable int classno, @PathVariable String studentname) {
        return schoolService.getStudentOfClassByName(classno, studentname);
    }

    @GetMapping("/getTopperByClassno/{classno}")
    @ResponseBody
    public Student getTopper(@PathVariable int classno) {
        return schoolService.getTopper(classno);
    }

    @PostMapping("/getAllstudents")
    public String getAll(Model model) {
        System.out.println("All students");
        List<Student> studentList = schoolService.getAllStudent();
        model.addAttribute("records", studentList);
        return "display";
    }

    @PostMapping(value = "/update")
    public String update(Model model, @RequestParam(value = "Id") long Id) {
        Student student = schoolService.getStudentById(Id);
        model.addAttribute("student", student);
        return "update";
    }

    public String page(Model model,ResponseOutput responseOutput) {
        if(!responseOutput.isSuccess()){
            model.addAttribute("msg", responseOutput.getErrorMessage());
            return "error";
        }
        List<Student> list = responseOutput.getStudentList();
        model.addAttribute("student", list.get(0));
        model.addAttribute("msg",responseOutput.getMessage());
        return "success";

    }

    @PostMapping(value = "/updateStudent", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateStudent(Model model, Student student) {
        ResponseOutput responseOutput = schoolService.updateStudent(student);
        return page(model, responseOutput);
    }


}
