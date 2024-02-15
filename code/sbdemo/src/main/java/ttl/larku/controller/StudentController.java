package ttl.larku.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author whynot
 */

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private URICreator uriCreator;

    @GetMapping("/boo/hoo")
    public String getBoHoo() {
        return "boo hoo";
    }

    @GetMapping
    public List<Student> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return students;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") int id) {
        Student s = studentService.getStudent(id);
        if(s == null) {
            return  ResponseEntity.badRequest().body("No student with id: " + id);
        }
        return ResponseEntity.ok(s);
    }

    @Autowired
    private Validator validator;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody /*@Valid*/ Student student) {
       Student newStudent = studentService.createStudent(student);

       //http://localhost:8080/students/5
//        URI newResource = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(student.getId())
//                .toUri();
        URI newResource = uriCreator.createURI(newStudent.getId());

       return ResponseEntity.created(newResource).build(); //body(newStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        boolean result = studentService.deleteStudent(id);
        if(!result) {
            return  ResponseEntity.badRequest().body("No student with id: " + id);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        boolean result = studentService.updateStudent(student);
        if(!result) {
            return  ResponseEntity.badRequest().body("No student with id: " + student.getId());
        }
        return ResponseEntity.noContent().build();
    }

//    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
//    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
//        var errors = ex.getFieldErrors();
//        List<String> errMsgs = errors.stream()
//                .map(error -> "@Valid error:" + error.getField() + ": " + error.getDefaultMessage()
//                        + ", supplied Value: " + error.getRejectedValue())
//                .collect(toList());
//        return ResponseEntity.badRequest().body(errMsgs);
//    }
}
