package ttl.larku.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;


import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StudentControllerUnitTest {

    @Mock
    private StudentService studentService;

    @Mock
    private URICreator uriCreator;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void testCreateStudent() throws URISyntaxException {
        Student student = new Student("Test Student");
        student.setId(1);
        String uriStr = "http://localhost:8080/students/1";

        URI uriResource = new URI(uriStr);
        Mockito.when(studentService.createStudent(student)).thenReturn(student);
        Mockito.when(uriCreator.createURI(student.getId())).thenReturn(uriResource);

        ResponseEntity<?> result = studentController.createStudent(student);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(uriStr, result.getHeaders().get("Location").get(0));
        Mockito.verify(studentService).createStudent(student);
    }

}
