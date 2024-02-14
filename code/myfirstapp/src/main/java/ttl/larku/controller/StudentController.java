package ttl.larku.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.net.URI;
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
    public ResponseEntity<?> getStudent(@PathVariable(name="sid") int sid) {
        Student student = studentService.getStudent(sid);
        if (student == null){
            return ResponseEntity.badRequest().body("No student with id: " + sid);
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student newStudent = studentService.createStudent(student);

        // http://localhost:8080/students/:id
        URI newResource = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newStudent.getId())
                .toUri();
        return ResponseEntity.created(newResource).build();
    }

    @DeleteMapping("/{sid}")
    public ResponseEntity<?> deleteStudent(@PathVariable(name="sid") int sid){
        boolean result = studentService.deleteStudent(sid);
        if (!result){
            return ResponseEntity.badRequest().body("No student with id: " + sid);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{sid}")
    public ResponseEntity<?> updateStudent(@PathVariable(name="sid") int sid, @RequestBody Student student){
        boolean result = studentService.updateStudent(student);
        if (!result){
            return ResponseEntity.badRequest().body("No student with id: " + sid);
        }
        return ResponseEntity.noContent().build();
    }
}
