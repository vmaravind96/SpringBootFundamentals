package ttl.larku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttl.larku.domain.Student;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    // Documentation: https://docs.spring.io/spring-data/jpa/reference/repositories/query-methods-details.html

    List<Student> findByName(String name);

    List<Student> findByNameContaining(String name);

    List<Student> findByNameContainingIgnoringCase(String name);
}
