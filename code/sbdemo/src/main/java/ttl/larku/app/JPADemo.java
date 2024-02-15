package ttl.larku.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.springframework.cglib.core.Local;
import ttl.larku.domain.Student;

import java.time.LocalDate;
import java.util.List;

/**
 * @author whynot
 */
public class JPADemo {


    public static void main(String[] args) {
        JPADemo jpaDemo = new JPADemo();
//        jpaDemo.addStudent();
        jpaDemo.updateStudent();
        jpaDemo.dumpStudents();
    }

    private EntityManagerFactory emf;

    public JPADemo() {
        emf = Persistence.createEntityManagerFactory("LarkUPU_SE");
    }

    public void dumpStudents() {
        EntityManager entityManager = emf.createEntityManager();

        TypedQuery<Student> query = entityManager.createQuery("select s from Student s", Student.class);
        List<Student> result = query.getResultList();

        System.out.println("result: " + result.size());
        result.forEach(System.out::println);

        entityManager.close();
    }

    public void addStudent() {
        Student student = new Student("Samual", "383 93939 03303", LocalDate.now(), Student.Status.FULL_TIME);

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(student);

        em.getTransaction().commit();

    }

    public void updateStudent() {
        EntityManager em = emf.createEntityManager();
        Student found = em.find(Student.class, 5);
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();


        found.setName("Something completely different");

        em.getTransaction().commit();
    }
}
