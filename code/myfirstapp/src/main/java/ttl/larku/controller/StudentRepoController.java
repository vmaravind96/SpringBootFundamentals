package ttl.larku.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentRepoService;
import ttl.larku.service.StudentService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/studentsrepo")
public class StudentRepoController {

    @Autowired
    private URICreator uriCreator;

    @Autowired
    private StudentRepoService studentService;

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
    public ResponseEntity<Student> createStudent(@RequestBody @Valid Student student){

        /* Ways to validate:
        1. Write our own custom validation logic
        2. Annotate @Valid so spring boot throws an error (we catch it in Global Exception handler)
        3. Error errors argument addition to the method param so SB gives us the errors.
        4. Autowire Validator and validateObject inside our method.
         */
        Student newStudent = studentService.createStudent(student);

        // http://localhost:8080/students/:id
        URI newResource = uriCreator.createURI(newStudent.getId());

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

    public boolean validateStudent(Student student) {
        return true;
    }
}

/*
*****************************
Custom configurations:
*****************************

(for code that we own)
1. Create a customConfig.properties file
2. Create a config reader component (see ConnectionServiceProperties.java) with proper annotation. (@ConfigurationProperties)
3. Auto wire the above component to your Service class (see ConnectionService.java)

(for code that we don't own)
1. Create a bean in some config (see LarkConfig) class
2. Add proper annotation to bean creation method (@ConfigurationProperties)

Flow: Bean creation -> add the properties mentioned in @Configuration properties.

*******************************
Actuators:
*******************************

Expose endpoints that show beans, health, metrics etc., (Refer application.properties management.endpoint prefix)
1. Go to localhost:8080/actuator or use the IntelliJ actuator tab in the run window.

 */
