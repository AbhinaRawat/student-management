package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        return "index"; // index.html in /templates
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add"; // add.html in /templates
    }

    @PostMapping("/add")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepo.save(student);
        return "redirect:/add";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Student student = studentRepo.findById(id).orElse(null);
        model.addAttribute("student", student);
        return "update"; // update.html in /templates
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute Student student) {
        student.setId(id);
        studentRepo.save(student);
        return "redirect:/update";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentRepo.deleteById(id);
        return "redirect:/";
    }
}
