package ttl.larku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

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
		System.out.println("Hello! from my commandline runner..!");
		List<Student> students = studentService.getAllStudents();
		System.out.println("Student size: " + students.size());
		for (Student student:  students) {
			System.out.println(student);
		}
	}
}
