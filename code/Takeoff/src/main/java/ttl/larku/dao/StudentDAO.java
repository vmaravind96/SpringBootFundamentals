package ttl.larku.dao;

import ttl.larku.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentDAO {
    boolean update(Student updateObject);

    boolean delete(Student student);

    Student create(Student newObject);

    Student get(int id);

    List<Student> getAll();

    Map<Integer, Student> getStudents();
}
