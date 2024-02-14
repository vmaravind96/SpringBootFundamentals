package ttl.larku.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{sid}")
    public Student getStudent(@PathVariable(name="sid") int sid) {
        return studentService.getStudent(sid);
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }
}
