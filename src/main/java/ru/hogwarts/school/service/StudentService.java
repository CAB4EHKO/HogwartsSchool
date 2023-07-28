package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long studentId = 0;

    public Student creatStudent(Student student) {

        student.setId(++studentId);
        students.put(studentId, student);
        return student;

    }

    public Student findStudent(long id) {

        return students.get(id);

    }

    public Student editStudent(Student Student) {

        if (students.containsKey(Student.getId())) {
            students.put(Student.getId(), Student);
            return Student;
        }
        return null;

    }

    public Student deleteStudent(long id) {

        return students.remove(id);

    }

    public Collection<Student> getAllStudent() {

        return students.values();

    }

    public List<Student> searchByAge(int age) {

        return students.values()
                .stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());

    }
}

