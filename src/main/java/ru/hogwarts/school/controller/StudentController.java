package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.impl.StudentServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {

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


    @GetMapping("/faculty/{id}")
    public ResponseEntity<Faculty> findFacultyByStudent(@PathVariable Long id) {
        Faculty foundFaculty = studentService.findFacultyByStudent(id);
        if (foundFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @GetMapping("allStudentCount")
    public int getSchoolStudentsCount() {
        return studentService.countAllStudentInTheSchool();
    }

    @GetMapping("averageAgeOfStudent")
    public double getAverageAgeOfStudent() {
        return studentService.getAverageAgeOfStudent();
    }

    @GetMapping("lastStudent")
    public List<Student> lastStudent() {
        return studentService.getLastStudent();
    }

    @GetMapping("nameStartsWithLetter")
    public ResponseEntity<List<String>> filterStudentsByNameStartsWith(@RequestParam(value = "letter") String letter) {
        List<String> filteredStudents = studentService.filterStudentsByNameStartsWith(letter);
        return ResponseEntity.ok(filteredStudents);
    }

    @GetMapping("averageAgeOfStudentsStreamAPI")
    public ResponseEntity<Double> getAverageAgeOfStudentsByStreamAPI() {
        double averageAge = studentService.getAverageAgeOfStudentsByStreamAPI();
        return ResponseEntity.ok(averageAge);
    }

    @GetMapping("threads")
    public void parallelTreads() {
        studentService.parallelThreads();
    }

    @GetMapping("threadsSynchronized")
    public void synchronizedTreads() {
        studentService.synchronizedTreads();
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
