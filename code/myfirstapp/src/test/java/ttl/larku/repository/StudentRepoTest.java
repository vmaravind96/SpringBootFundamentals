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

    @Test
    public void testFindByName() {
        List<Student> byName = studentRepo.findByName("Manoj-h2");
        System.out.println("byName: " + byName);
        assertEquals(1, byName.size());
    }

    @Test
    public void testFindByNameContaining() {
        List<Student> byName = studentRepo.findByNameContaining("Manoj");
        System.out.println("byName: " + byName);
        assertEquals(1, byName.size());
    }

    @Test
    public void testFindByNameContainingIgnoringCase() {
        List<Student> byName = studentRepo.findByNameContainingIgnoringCase("manoj");
        System.out.println("byName: " + byName);
        assertEquals(1, byName.size());
    }
}
