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

    @GetMapping
    public ResponseEntity findStudent(@RequestParam(required = false) Integer age,
                                      @RequestParam(required = false) Integer minAge,
                                      @RequestParam(required = false) Integer maxAge) {
        if (age != null) {
            if (age >= 0) {
                return ResponseEntity.ok(studentService.findByAge(age));
            } else {
                return ResponseEntity.badRequest().body("Invalid age value");
            }
        }
        if (minAge != null && maxAge != null) {
            if (minAge >= 0 && maxAge >= 0) {
                return ResponseEntity.ok(studentService.findByAgeBetween(minAge, maxAge));
            } else {
                return ResponseEntity.badRequest().body("Invalid age range values");
            }
        }
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
