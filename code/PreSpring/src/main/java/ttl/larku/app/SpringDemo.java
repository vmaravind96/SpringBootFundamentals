package ttl.larku.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.List;

public class SpringDemo {
    public static void main(String[] args){
        SpringDemo sd = new SpringDemo();
        sd.goStudent();
    }

    public void goStudent() {
        // First thing to use a spring. Points this to the configuration / bean declaration
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // Reflect API calls the constructor
        StudentService studentService = context.getBean("studentService", StudentService.class);

        List<Student> students = studentService.getAllStudents();
        System.out.println("Students: " + students.size());
        for (Student student: students){
            System.out.println("Student info: " + student.toString());
        }

    }
}
