package ttl.larku.dao;

import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.dao.jpa.JpaStudentDAO;
import ttl.larku.service.StudentService;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author whynot
 */
public class DAOFactory {

    public static Map<String, Object> objects = new ConcurrentHashMap<>();

    public static StudentDAO studentDAO() {
        ResourceBundle bundle = ResourceBundle.getBundle("larkUContext");
        String profile = bundle.getString("larku.profile.active");
        return switch(profile) {
            case "dev" -> {
                var dao = objects.computeIfAbsent("studentDAO", k -> {
                    return new InMemoryStudentDAO();
                });
                yield (StudentDAO)dao;
            }
            case "prod" -> {
                var dao = objects.computeIfAbsent("studentDAO", k -> {
                    return new JpaStudentDAO();
                });
                yield (StudentDAO)dao;
            }
            default -> throw new RuntimeException("Unknown profile: " + profile);
        };
    }

    public static StudentService studentService() {

        StudentService studentService = (StudentService)objects.computeIfAbsent("studentService", k -> {
            StudentService ss = new StudentService();
            StudentDAO studentDAO = studentDAO();
            ss.setStudentDAO(studentDAO);
            return ss;
        });

        return studentService;
    }
}
