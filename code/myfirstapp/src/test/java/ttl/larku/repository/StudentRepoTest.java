package ttl.larku.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ttl.larku.domain.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StudentRepoTest {

    @Autowired
    private StudentRepo studentRepo;

    @Test
    public void testFindAll(){
        List<Student> result = studentRepo.findAll();
        assertEquals(4, result.size());
    }
}
