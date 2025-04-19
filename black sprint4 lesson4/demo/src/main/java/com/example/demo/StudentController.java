package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private JdbcStudentRepository studentRepository;

    @GetMapping(value = { "/user", "/admin" })
    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    @GetMapping(value = { "/user/{id}", "/admin/{id}" })
    public Student getStudentById(@PathVariable("id") long studentId) {
        return studentRepository.getStudentById(studentId);
    }

    @PutMapping("/admin/{id}/grade")
    public void updateStudentGrade(@PathVariable("id") long studentId, @RequestBody String newGrade) {
        studentRepository.updateStudentGrade(studentId, newGrade);
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentRepository.saveStudent(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        student.setStudentId(id);
        studentRepository.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") long id) {
        studentRepository.deleteStudentById(id);
    }
}
