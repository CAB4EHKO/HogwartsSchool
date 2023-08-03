package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;

    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {

        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);

    }

    @GetMapping("/search/{age}")
    public List<Student> findByAge(@PathVariable Integer age) {

        return studentService.findByAge(age);

    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudents() {

        return ResponseEntity.ok(studentService.getAllStudent());

    }

    @PostMapping
    public Student creatStudent(@RequestBody Student student) {

        return studentService.creatStudent(student);

    }

    @PutMapping("{id}")
    public ResponseEntity<Student> editStudent(@PathVariable Long id, @RequestBody Student student) {

        Student foundStudent = studentService.editStudent(id, student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);

    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {

       studentService.deleteStudent(id);
        return ResponseEntity.ok().build();

    }
}