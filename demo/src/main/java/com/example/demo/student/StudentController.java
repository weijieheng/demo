package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Controller // To use GetMapping, etc
@RequestMapping("/index")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/") //To serve as restful endpoint
    public String index(Model model) {
        model.addAttribute("students", studentService.getStudents());
        return "StudentPage";
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "/{studentID}")
    public void deleteStudent(@PathVariable("studentID") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "/{studentID}")
    public void updateStudent(
            @PathVariable("studentID") Long studentId,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name){
        studentService.updateStudent(studentId, email, name);
    }
}
