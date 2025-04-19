package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ViewController {

    @Autowired
    private JdbcStudentRepository studentRepository;

    @GetMapping("/students")
    public String students(Model model, Authentication authentication) {
        // ✅ This fetches the data
        List<Student> students = studentRepository.getAllStudents();
        model.addAttribute("students", students);

        // Role info (optional if you're doing role-based UI stuff)
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        model.addAttribute("roles", roles);
        model.addAttribute("newStudent", new Student());

        return "students";
    }

}
