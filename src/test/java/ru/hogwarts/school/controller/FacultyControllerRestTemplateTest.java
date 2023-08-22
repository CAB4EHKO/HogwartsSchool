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
import ru.hogwarts.school.model.Faculty;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {

        assertThat(facultyController).isNotNull();
    }

    @Test
    public void testGetStudentByParams() throws Exception {

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty", String.class))
                .isNotEmpty();

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/1", String.class))
                .isNotNull()
                .isNotBlank();

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/?name=gryffindor", String.class))
                .isNotNull();

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/?color=gold", String.class))
                .isNotNull();

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/?name=gryffindor&color=gold", String.class))
                .isNotNull();

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/student/1", String.class))
                .isNotNull();



    }

    @Test
    void testPostFaculty() throws Exception {
        Faculty faculty = new Faculty();

        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotNull();

    }

    @Test
    void testEditFaculty() throws Exception {
        Faculty faculty = new Faculty();

        ResponseEntity<Faculty> response = restTemplate.exchange(
                "/faculty/6",
                HttpMethod.PUT,
                new HttpEntity<>(faculty),
                Faculty.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    @Test
    void testDeleteStudent() throws Exception {
        Faculty faculty = new Faculty();

        ResponseEntity<Faculty> response = restTemplate.exchange(
                "/faculty/48",
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Faculty.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

}
