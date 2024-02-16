package ttl.larku.service;

import org.springframework.stereotype.Service;
import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.Student;
import ttl.larku.repository.StudentRepo;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentRepoService {

    private StudentRepo studentRepo;

    public StudentRepoService(StudentRepo repo) {
        this.studentRepo = repo;
    }

    private CourseService cs;

    public Student createStudent(String name, String phoneNumber, Student.Status status) {
        Student student = new Student(name, phoneNumber, status);
        student = createStudent(student);

        return student;
    }

    public Student createStudent(String name, String phoneNumber, LocalDate dob, Student.Status status) {
        Student student = new Student(name, phoneNumber, dob, status);
        student = createStudent(student);

        return student;
    }

    public Student createStudent(Student student) {
        student = studentRepo.save(student);

        return student;
    }

    public boolean deleteStudent(int id) {
        Student student = studentRepo.findById(id).orElse(null);
        if (student != null) {
            studentRepo.delete(student);
            return true;
        }
        return false;
    }

    public boolean updateStudent(Student newStudent) {
        Student student = studentRepo.findById(newStudent.getId()).orElse(null);
        if (student != null) {
            studentRepo.save(newStudent);
            return true;
        }
        return false;
    }

    public Student getStudent(int id) {
        return studentRepo.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public StudentRepo getStudentRepo() {
        return studentRepo;
    }

    public void setStudentRepo(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

//    public void clear() {
//        studentRepo.deleteStore();
//        studentRepo.createStore();
//    }

    public CourseService getCs() {
        return cs;
    }

    public void setCs(CourseService cs) {
        this.cs = cs;
    }
}
