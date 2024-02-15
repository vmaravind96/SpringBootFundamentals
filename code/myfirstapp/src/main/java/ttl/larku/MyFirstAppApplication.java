package ttl.larku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class MyFirstAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFirstAppApplication.class, args);
	}

}

@Component
class MyRunner implements CommandLineRunner{

	@Autowired
	private StudentService studentService;

	@Override
	public void run(String... args) throws Exception {
		var initStudents = List.of (
				new Student("Aravind", "999 999 9999", LocalDate.of(1960, 10, 10), Student.Status.FULL_TIME),
				new Student("Nick", "999 999 9999", LocalDate.of(1965, 6, 11), Student.Status.PART_TIME),
				new Student("Yunsun", "999 999 9999", LocalDate.of(1969, 8, 5), Student.Status.HIBERNATING)
		);
		initStudents.forEach(s -> studentService.createStudent(s));
		System.out.println("Hello! from my commandline runner..!");
		List<Student> students = studentService.getAllStudents();
		System.out.println("Student size: " + students.size());
		for (Student student:  students) {
			System.out.println(student);
		}
	}
}


/*
***********************
JPA Demo
***********************
1. Create META-INF folder and add Manifest.mf and persistence.xml file.
2. Put the XML config for the corresponding database in the persistence.xml file
3. Create EntityManagerFactory using this command:
	* EntityManagerFactory emf = Persistence.createEntityManagerFactory("LarkUPU_SE");
	* EntityManager em = emf.createEntityManager();
4. Then refer to the examples in this repo to see how to query and add, update / delete
5. Note:
	* Student has to be an entity inorder for this to work.
	* Transactions are very important
	* Use and discard entityManager after use.
 */
