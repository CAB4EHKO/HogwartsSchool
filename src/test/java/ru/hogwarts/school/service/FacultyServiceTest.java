package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//public class FacultyServiceTest {

//    private FacultyService facultyService;
//
//    @BeforeEach
//    void setUp() {
//
//        facultyService = new FacultyService();
//
//    }
//
//    @Test
//    void testCreatFaculty() {
//
//        Faculty faculty = new Faculty(1, "One", "Red");
//        Faculty createdFaculty = facultyService.creatFaculty(faculty);
//
//        assertNotNull(createdFaculty.getId());
//        assertEquals(1, createdFaculty.getId());
//        assertEquals(faculty.getName(), createdFaculty.getName());
//        assertEquals(faculty.getColor(), createdFaculty.getColor());
//
//    }
//
//    @Test
//    void testFindFaculty() {
//
//        Faculty faculty = new Faculty(1, "One", "Red");
//        Faculty createdFaculty = facultyService.creatFaculty(faculty);
//
//        Faculty foundFaculty = facultyService.findFaculty(createdFaculty.getId());
//
//        assertNotNull(foundFaculty);
//        assertEquals(createdFaculty, foundFaculty);
//
//    }
//
//    @Test
//    void testEditFaculty() {
//        Faculty faculty = new Faculty(1, "One", "Red");
//        facultyService.creatFaculty(faculty);
//
//        Faculty editedFaculty = new Faculty(1, "Two", "Yellow");
//        Faculty result = facultyService.editFaculty(1, editedFaculty);
//
//        assertEquals(editedFaculty, result);
//    }
//
//    @Test
//    void testEditFacultyNonExistent() {
//        Faculty editedFaculty = new Faculty(1, "One", "Yellow");
//        Faculty result = facultyService.editFaculty(1, editedFaculty);
//
//        assertNull(result);
//    }
//
//    @Test
//    void testDeleteFaculty() {
//
//        Faculty faculty = new Faculty(1, "One", "Red");
//        Faculty createdFaculty = facultyService.creatFaculty(faculty);
//
//        Faculty deleteFaculty = facultyService.deleteFaculty(createdFaculty.getId());
//
//        assertNotNull(deleteFaculty);
//        assertEquals(createdFaculty, deleteFaculty);
//        assertNull(facultyService.findFaculty(createdFaculty.getId()));
//
//    }
//
//    @Test
//    void testGetAllStudent() {
//
//        Faculty faculty1 = new Faculty(1, "One", "Red");
//        Faculty faculty2 = new Faculty(2, "Two", "Black");
//
//        facultyService.creatFaculty(faculty1);
//        facultyService.creatFaculty(faculty2);
//
//        Collection<Faculty> getAllFaculty = facultyService.getAllFaculty();
//
//        assertEquals(2, getAllFaculty.size());
//        assertTrue(getAllFaculty.contains(faculty1));
//        assertTrue(getAllFaculty.contains(faculty2));
//
//    }
//
//    @Test
//    void testSearchByColor() {
//
//        Faculty faculty1 = new Faculty(1, "One", "Red");
//        Faculty faculty2 = new Faculty(2, "Two", "Black");
//        Faculty faculty3 = new Faculty(3, "Three", "Red");
//
//        facultyService.creatFaculty(faculty1);
//        facultyService.creatFaculty(faculty2);
//        facultyService.creatFaculty(faculty3);
//
//        List<Faculty> facultyWithColorRed = facultyService.searchByColor("Red");
//
//        assertEquals(2, facultyWithColorRed.size());
//        assertTrue(facultyWithColorRed.contains(faculty1));
//        assertTrue(facultyWithColorRed.contains(faculty3));
//
//    }
//
//}
//
