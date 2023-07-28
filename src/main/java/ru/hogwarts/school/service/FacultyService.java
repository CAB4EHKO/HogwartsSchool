package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long facultyId = 0;

    public Faculty creatFaculty(Faculty faculty) {

        faculty.setId(++facultyId);
        faculties.put(facultyId, faculty);
        return faculty;

    }

    public Faculty findFaculty(long id) {

        return faculties.get(id);

    }

    public Faculty editFaculty(Faculty faculty) {

        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;

    }

    public Faculty deleteFaculty(long id) {

        return faculties.remove(id);

    }

    public Collection<Faculty> getAllFaculty() {

        return faculties.values();

    }

    public List<Faculty> searchByColor(String color) {

        return faculties.values()
                .stream()
                .filter(student -> student.getColor().equals(color))
                .collect(Collectors.toList());

    }
}
