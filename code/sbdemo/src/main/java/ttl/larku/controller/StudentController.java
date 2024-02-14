package ttl.larku.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.List;

/**
 * @author whynot
 */

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return students;
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") int id) {
        Student s = studentService.getStudent(id);
        return s;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
       Student newStudent = studentService.createStudent(student);
       return newStudent;
    }
}
