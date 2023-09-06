package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Student creatStudent(Student student) {
        logger.info("Create faculty: {} ", student);
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(long id) {
        logger.info("Request student with ID: {}", id);
        return studentRepository.findById(id).get();
    }

    @Override
    public Student editStudent(long id, Student student) {
        logger.info("Requested method: EditStudent");
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Requested method: deleteStudent");
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getAllStudent() {
        logger.info("Requested method: getAllStudent");
        return studentRepository.findAll();
    }

    @Override
    public Collection<Student> findByAge(int age) {
        logger.info("Requested method: findByAge");
        return studentRepository.findByAge(age);
    }

    @Override
    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        logger.info("Requested method: findByAgeBetween");
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }


    @Override
    public Faculty findFacultyByStudent(Long id) {
        logger.info("Requested method: findFacultyByStudent");
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get().getFaculty();
        } else {
            return null;
        }
    }

    @Override
    public int countAllStudentInTheSchool() {
        logger.info("Requested method: countAllStudentInTheSchool");
        return studentRepository.countAllStudentInTheSchool();
    }

    @Override
    public double getAverageAgeOfStudent() {
        logger.info("Requested method: getAverageAgeOfStudent");
        return studentRepository.getAverageAgeOfStudent();
    }

    @Override
    public List<Student> getLastStudent() {
        logger.info("Requested method: getLastStudent");
        return studentRepository.getLastStudent();
    }

    @Override
    public List<String> filterStudentsByNameStartsWith(String letter) {
        logger.info("Requested method: filterStudentsByNameStartsWith");
        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .filter(name -> name.substring(0, 1).equalsIgnoreCase(letter))
                .sorted()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    @Override
    public double getAverageAgeOfStudentsByStreamAPI() {
        logger.info("Requested method: getAverageAgeOfStudentsByStreamAPI");
        return studentRepository.findAll()
                .stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0.0);
    }
}