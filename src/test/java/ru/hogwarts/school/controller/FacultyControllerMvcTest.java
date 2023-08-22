package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.impl.FacultyServiceImpl;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(FacultyController.class)
public class FacultyControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyServiceImpl facultyService;

    @Test
    public void testGetInfoFaculty() throws Exception {
        long facultyId = 1L;
        Faculty faculty = new Faculty();
        faculty.setId(facultyId);
        faculty.setName("Engineering");
        when(facultyService.findFaculty(facultyId)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/{id}", facultyId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(facultyId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Engineering"));
    }

    @Test
    public void testFindFaculties() throws Exception {
        when(facultyService.getAllFaculty()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty")
                        .param("name", "Engineering")
                        .param("color", "Red"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    public void testFindStudentByFaculty() throws Exception {
        long facultyId = 1L;
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John");
        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Alice");
        when(facultyService.findStudentByFaculty(facultyId)).thenReturn(List.of(student1, student2));

        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/student/{facultyId}", facultyId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Alice"));
    }

    @Test
    public void testCreateFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Science");
        String facultyJson = "{ \"name\": \"Science\", \"color\": \"Blue\" }";
        when(facultyService.creatFaculty(Mockito.any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders.post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(facultyJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Science"));
    }

    @Test
    public void testEditFaculty() throws Exception {
        Faculty facultyToUpdate = new Faculty();
        facultyToUpdate.setId(1L);
        facultyToUpdate.setName("Engineering");

        Faculty updatedFaculty = new Faculty();
        updatedFaculty.setId(1L);
        updatedFaculty.setName("Computer Science");

        when(facultyService.editFaculty(1L, facultyToUpdate)).thenReturn(updatedFaculty);

        String updatedFacultyJson = "{\"id\": 1, \"name\": \"Computer Science\", \"color\": \"Green\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/faculty/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedFacultyJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Computer Science"));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        long facultyId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/faculty/{id}", facultyId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
