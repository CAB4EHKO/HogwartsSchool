package ru.hogwarts.school.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Student;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerRestTemplateTest {


    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {

        assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudentByParams() throws Exception {

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotEmpty();

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/6", String.class))
                .isNotNull()
                .isNotBlank();

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/faculty/5", String.class))
                .isNotNull();

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/?age=11", String.class))
                .isNotNull();

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/?min=10&max=20", String.class))
                .isNotNull();
    }

    @Test
    void testPostStudent() throws Exception {
        Student student = new Student();

        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();

    }

    @Test
    void testEditStudent() throws Exception {
        Student student = new Student();

        ResponseEntity<Student> response = restTemplate.exchange(
                "/student/6",
                HttpMethod.PUT,
                new HttpEntity<>(student),
                Student.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    @Test
    void testDeleteStudent() throws Exception {
        Student student = new Student();

        ResponseEntity<Student> response = restTemplate.exchange(
                "/student/38",
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Student.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

}
