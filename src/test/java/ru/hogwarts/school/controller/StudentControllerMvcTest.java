package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.impl.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(StudentController.class)
public class StudentControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentServiceImpl studentService;

    @Test
    public void testGetStudentInfo() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("John");
        student.setAge(20);

        when(studentService.findStudent(1L)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(20));
    }

    @Test
    public void testFindStudentByAge() throws Exception {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John");
        student1.setAge(20);

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Alice");
        student2.setAge(22);

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        when(studentService.findByAge(20)).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/student?age=20"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Alice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].age").value(22));
    }

    @Test
    public void testFindStudentByAgeRange() throws Exception {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John");
        student1.setAge(20);

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Alice");
        student2.setAge(22);

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        when(studentService.findByAgeBetween(20, 25)).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/student?minAge=20&maxAge=25"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Alice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].age").value(22));
    }

    @Test
    public void testFindFacultyByStudent() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Engineering");

        when(studentService.findFacultyByStudent(1L)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/faculty/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Engineering"));
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Bob");
        student.setAge(21);


        when(studentService.creatStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Bob\",\"age\":21}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bob"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(21));
    }

    @Test
    public void testEditStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Bob");
        student.setAge(21);

        when(studentService.editStudent(1L, student)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.put("/student/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Bob\",\"age\":21}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bob"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(21));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/student/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
