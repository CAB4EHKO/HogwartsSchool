package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.impl.FacultyServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyServiceImpl facultyService;

    public FacultyController(FacultyServiceImpl facultyService) {

        this.facultyService = facultyService;

    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getInfoFaculty(@PathVariable Long id) {

        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);

    }

    @GetMapping
    public ResponseEntity findFaculties(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String color) {
        if ((name != null && !name.isBlank()) || (color != null && !color.isBlank())) {
            return ResponseEntity.ok(facultyService.findFaculties(name, color));
        }
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping("/student/{facultyId}")
    public ResponseEntity<Collection<Student>> findStudentByFaculty(@PathVariable Long facultyId) {

        Collection<Student> foundStudents = facultyService.findStudentByFaculty(facultyId);

        if (foundStudents.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudents);
    }

//    @GetMapping("longestFaculty")
//    public ResponseEntity<String> getLongestFacultyName() {
//        String nameFaculty = facultyService.getLongestFacultyName();
//        return ResponseEntity.ok(nameFaculty);
//    }

    @GetMapping("longestFacultyName")
    public ResponseEntity<List<String>> getLongestFacultyName() {
        List<String> nameFaculty = facultyService.getLongestFacultyName();
        return ResponseEntity.ok(nameFaculty);
    }


    @PostMapping
    public Faculty creatFaculty(@RequestBody Faculty faculty) {
        return facultyService.creatFaculty(faculty);

    }


    @PutMapping("{id}")
    public ResponseEntity<Faculty> editFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.editFaculty(id, faculty);

        if (updatedFaculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(updatedFaculty);
    }
//    public ResponseEntity<Faculty> editFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
//
//        Faculty foundFaculty = facultyService.editFaculty(id, faculty);
//        if (foundFaculty == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        return ResponseEntity.ok(foundFaculty);
//
//    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id) {

        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
}
