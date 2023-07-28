package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    void setUp() {

        studentService = new StudentService();

    }

    @Test
    void testCreateStudent() {

        Student student = new Student(1, "Garry", 10);
        Student createdStudent = studentService.creatStudent(student);

        assertNotNull(createdStudent.getId());
        assertEquals(1, createdStudent.getId());
        assertEquals(student.getName(), createdStudent.getName());
        assertEquals(student.getAge(), createdStudent.getAge());

    }

    @Test
    void testFindStudent() {

        Student student = new Student(1, "Garry", 10);
        Student createdStudent = studentService.creatStudent(student);

        Student foundStudent = studentService.findStudent(createdStudent.getId());

        assertNotNull(foundStudent);
        assertEquals(createdStudent, foundStudent);

    }

    @Test
    void testEditStudent() {

        Student student = new Student(1, "Garry", 10);
        Student createdStudent = studentService.creatStudent(student);

        createdStudent.setName("Garryny");
        Student editedStudent = studentService.editStudent(createdStudent);

        assertNotNull(editedStudent);
        assertEquals(createdStudent, editedStudent);
        assertEquals("Garryny", editedStudent.getName());

    }

    @Test
    void testEditStudentNonExistent() {

        Student student = new Student(1, "Garry", 10);
        Student editedStudent = studentService.editStudent(student);

        assertNull(editedStudent);

    }

    @Test
    void testDeleteStudent() {

        Student student = new Student(1, "Garry", 10);
        Student createdStudent = studentService.creatStudent(student);

        Student deletedStudent = studentService.deleteStudent(createdStudent.getId());

        assertNotNull(deletedStudent);
        assertEquals(createdStudent, deletedStudent);
        assertNull(studentService.findStudent(createdStudent.getId()));

    }

    @Test
    void testGetAllStudent() {

        Student student1 = new Student(1, "Garry", 10);
        Student student2 = new Student(2, "Ron", 11);

        studentService.creatStudent(student1);
        studentService.creatStudent(student2);

        Collection<Student> allStudents = studentService.getAllStudent();

        assertEquals(2, allStudents.size());
        assertTrue(allStudents.contains(student1));
        assertTrue(allStudents.contains(student2));

    }

    @Test
    void testSearchByAge() {

        Student student1 = new Student(1, "Garry", 10);
        Student student2 = new Student(2, "Ron", 11);
        Student student3 = new Student(3, "Hermione", 10);

        studentService.creatStudent(student1);
        studentService.creatStudent(student2);
        studentService.creatStudent(student3);

        List<Student> studentsWithAge10 = studentService.searchByAge(10);

        assertEquals(2, studentsWithAge10.size());
        assertTrue(studentsWithAge10.contains(student1));
        assertTrue(studentsWithAge10.contains(student3));

    }
}
