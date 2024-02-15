package ttl.larku.dao.jpahibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class JPAStudentDAO implements BaseDAO<Student> {

    @PersistenceContext
    private EntityManager entityManger;

    private String from;

    public JPAStudentDAO(String from) {
        this.from = from + ": ";
    }

    public JPAStudentDAO() {
        this("JPA");
    }

    public boolean update(Student updateObject) {
        entityManger.merge(updateObject);
        return true;
    }

    public boolean delete(Student student) {
        entityManger.remove(student);
        return true;
    }

    public Student create(Student newObject) {
        //Put our Mark
        newObject.setName(from + newObject.getName());

        entityManger.persist(newObject);

        return newObject;
    }

    public Student get(int id) {
        return entityManger.find(Student.class, id);
    }

    public List<Student> getAll() {
        TypedQuery<Student> query = entityManger.createQuery("select s from Student s", Student.class);
        return query.getResultList();
    }

    public void deleteStore() {
//        students = null;
    }

    public void createStore() {
//        students = new ConcurrentHashMap<>();
//        nextId = new AtomicInteger(1);
    }

//    public Map<Integer, Student> getStudents() {
//        return students;
//    }
}
