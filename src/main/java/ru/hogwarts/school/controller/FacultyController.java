package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {

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

    @GetMapping("/search/{color}")
    public List<Faculty> searchByColor(@PathVariable String color) {

        return facultyService.searchByColor(color);

    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {

        return ResponseEntity.ok(facultyService.getAllFaculty());

    }

    @PostMapping
    public Faculty creatFaculty(Faculty faculty) {

        return facultyService.creatFaculty(faculty);

    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(Faculty faculty) {

        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {

        Faculty removeFaculty = facultyService.deleteFaculty(id);
        if (removeFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(removeFaculty);

    }
}
