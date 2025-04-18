package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private JdbcStudentRepository studentRepository;

    @GetMapping("/students")
    public String studentsPage(Model model) {
        List<Student> students = studentRepository.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "Form"; 
    }
}
