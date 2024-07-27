package com.miguelprojects.controller;

import com.miguelprojects.model.Student;
import com.miguelprojects.repository.StudentRepository;
import com.miguelprojects.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Student postStudent(@Valid @RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentById(@RequestParam int id) {
        return studentRepository.findById(id).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @GetMapping("/nameAndAge")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getStudentsByNameAndAge(@RequestParam(required = false, defaultValue = "", name = "name") String name,
                                                 @RequestParam(required = false, name = "age") Optional<Integer> age) {

        return studentService.findStudentsByNameAndAge(name , age);
    }

}
