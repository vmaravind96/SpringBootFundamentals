package ttl.larku.dao.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ttl.larku.domain.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author whynot
 */
@SpringBootTest
public class StudentRepoTest {

    @Autowired
    private StudentRepo studentRepo;

    @Test
    public void testFindAll() {
        List<Student> result = studentRepo.findAll();
        System.out.println("result: " + result.size());
        result.forEach(System.out::println);
        assertEquals(4, result.size());
    }
}
