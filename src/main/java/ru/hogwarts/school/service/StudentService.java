package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private final StudentRepository studentRepository;

    public Student creatStudent(Student student) {

        return studentRepository.save(student);

    }

    public Student findStudent(long id) {

        return studentRepository.findById(id).get();

    }

    public Student editStudent(long id, Student student) {

        return studentRepository.save(student);

    }

    public void deleteStudent(long id) {

        studentRepository.deleteById(id);

    }

    public Collection<Student> getAllStudent() {

        return studentRepository.findAll();

    }

    public List<Student> searchByAge(int age) {

        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());

    }
}

