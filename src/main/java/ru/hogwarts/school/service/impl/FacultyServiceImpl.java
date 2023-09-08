package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty creatFaculty(Faculty faculty) {
        logger.info("Create faculty: {} ", faculty);
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(long id) {
        logger.info("Request faculty with ID: {}", id);
        Faculty faculty = facultyRepository.findById(id).get();
        logger.info("Faculty with ID: {} is {}", id, faculty);
        return faculty;
    }

    @Override
    public Faculty editFaculty(long id, Faculty faculty) {
        logger.info("Faculty with id: {} edited.", id);
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty existingFaculty = optionalFaculty.get();
            existingFaculty.setName(faculty.getName());
            existingFaculty.setColor(faculty.getColor());
            return facultyRepository.save(existingFaculty);
        }
        return null;
    }
//    public Faculty editFaculty(long id, Faculty faculty) {
//        return facultyRepository.save(faculty);
//    }

    @Override
    public void deleteFaculty(long id) {
        logger.error("There is not faculty with id: {}", id);
        logger.info("Delete faculty with id: {}", id);
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getAllFaculty() {
        logger.info("Request a list of faculties");
        return facultyRepository.findAll();
    }

    @Override
    public Collection<Faculty> findFaculties(String name, String color) {
        logger.info("Faculty search request");
        logger.warn("There is not faculty with color = " + color);
        logger.warn("There is not faculty with name = " + name);
        return facultyRepository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    @Override
    public Collection<Student> findStudentByFaculty(Long id) {
        logger.info("Requested a list of faculty students with id: {}", id);
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            return faculty.get().getStudents();
        } else {
            return Collections.emptyList();
        }
    }

//    @Override
//    public String getLongestFacultyName() {
//        return facultyRepository.findAll()
//                .stream()
//                .map(Faculty::getName)
//                .max(Comparator.comparing(String::length))
//                .orElse("");
//    }

    @Override
    public List<String> getLongestFacultyName() {
        logger.info("Requested method: getLongestFacultyName");
        List<Faculty> faculties = facultyRepository.findAll();

        if (faculties.isEmpty()) {
            return Collections.emptyList();
        }

        int maxLength = faculties.stream()
                .map(Faculty::getName)
                .mapToInt(String::length)
                .max()
                .orElse(0);

        return faculties.stream()
                .filter(faculty -> faculty.getName().length() == maxLength)
                .map(Faculty::getName)
                .collect(Collectors.toList());
    }
}

