package ttl.larku.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author whynot
 */
@ExtendWith(MockitoExtension.class)
public class StudentControllerUnitTest {

    @Mock
    private StudentService studentService;

    @Mock
    private URICreator uriCreator;

    @InjectMocks
    private StudentController controller;

    @Test
    public void testCreateStudent() throws URISyntaxException {
        Student student = new Student("Sarla");
        student.setId(5);
        Mockito.when(studentService.createStudent(student)).thenReturn(student);
        String uriStr = "http://localhost:8080/students/5";
        URI uri = new URI(uriStr);
        Mockito.when(uriCreator.createURI(5)).thenReturn(uri);

        ResponseEntity<?> result = controller.createStudent(student);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(uriStr, result.getHeaders().get("Location").get(0));

        Mockito.verify(studentService).createStudent(student);
    }
}
