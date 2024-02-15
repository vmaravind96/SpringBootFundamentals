package ttl.larku.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ttl.larku.domain.Course;
import ttl.larku.domain.Student;
import ttl.larku.jconfig.LarkUConfig;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;

import java.util.List;

public class SpringDemo {
    public static void main(String[] args){
        SpringDemo sd = new SpringDemo();
        sd.goStudent();
        sd.goCourse();
    }

    /*
    // XML based
    public void goStudent() {
            // First thing to use a spring. Points this to the configuration / bean declaration
            // Note: By default in XML,
            // 1. the scope is "singleton" we can change it to "proto" if we need one each time
            // 2. lazy-init="false" we can change it to true if needed.
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            // Reflect API calls the constructor
            StudentService studentService = context.getBean("studentService", StudentService.class);

            List<Student> students = studentService.getAllStudents();
            System.out.println("Students: " + students.size());
            for (Student student: students){
                System.out.println("Student info: " + student.toString());
            }
        }
     */

    // Configuration based
    public void goStudent() {
        // To read from a @Configuration class
        ApplicationContext context = new AnnotationConfigApplicationContext(LarkUConfig.class);
        // Reflect API calls the constructor
        StudentService studentService = context.getBean("studentService", StudentService.class);

        List<Student> students = studentService.getAllStudents();
        System.out.println("Students: " + students.size());
        for (Student student: students){
            System.out.println("Student info: " + student.toString());
        }
    }

    public void goCourse() {
        // To read from a @Configuration class
        // Need to inform Spring where to look for Stuff
        ApplicationContext context = new AnnotationConfigApplicationContext(LarkUConfig.class);
        // Reflect API calls the constructor
        CourseService courseService = context.getBean("courseService", CourseService.class);

        List<Course> courses = courseService.getAllCourses();
        System.out.println("Courses: " + courses.size());
        for (Course course: courses){
            System.out.println("course info: " + course.toString());
        }
    }
}
