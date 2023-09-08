package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FacultyService {
    Faculty creatFaculty(Faculty faculty);

    Faculty findFaculty(long id);

    Faculty editFaculty(long id, Faculty faculty);

    void deleteFaculty(long id);

    Collection<Faculty> getAllFaculty();

    Collection<Faculty> findFaculties(String name, String color);
    Collection<Student> findStudentByFaculty(Long id);

//    String getLongestFacultyName();

    List<String> getLongestFacultyName();
}
