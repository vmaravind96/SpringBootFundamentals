package ttl.larku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SbdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbdemoApplication.class, args);
	}
}

//REST   -  REpresentational State Transfer

@Component
class MyRunner implements CommandLineRunner
{
	@Autowired
	private StudentService studentService;

	@Override
	public void run(String... args) throws Exception {
		var initStudents = List.of (
			new Student("Joey", "383 939 9393", LocalDate.of(1960, 10, 10), Student.Status.FULL_TIME),
					new Student("Yuk", "886 939 9393", LocalDate.of(1979, 8, 10), Student.Status.PART_TIME),
					new Student("Firdaus", "888888 939 9393", LocalDate.of(1960, 10, 10), Student.Status.HIBERNATING),
				new Student("Nelson", "877586 9393", LocalDate.of(2000, 10, 10), Student.Status.HIBERNATING)
		);
		initStudents.forEach(studentService::createStudent);

		System.out.println("MyRunner says hello");
		List<Student> students = studentService.getAllStudents();
		System.out.println("students: " + students.size());
		students.forEach(System.out::println);
	}
}
