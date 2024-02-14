package ttl.larku.dao;

import ttl.larku.dao.inmemory.InMemoryCourseDAO;
import ttl.larku.dao.jpa.JpaStudentDAO;
import ttl.larku.service.StudentService;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public class DAOFactory {

    public static Map<String, Object> objects = new ConcurrentHashMap<>();

    public static StudentDAO getStudentDAO(){
        ResourceBundle bundle = ResourceBundle.getBundle("larkUContext");
        String profile = bundle.getString("larku.profile.active");
        StudentDAO studentDAO;
        return switch (profile) {
            case "dev" -> {
                 studentDAO = (StudentDAO) objects.computeIfAbsent("studentDAO", k -> {
                    return new InMemoryCourseDAO();
                });
                yield studentDAO;
            }
            case "prod" -> {
                studentDAO = (StudentDAO) objects.computeIfAbsent("studentDAO", k -> {
                    return new JpaStudentDAO();
                });
                yield studentDAO;
            }
            default -> throw new RuntimeException("Unknown profile: " + profile);
        };
    }

    public static StudentService getStudentService(){
        StudentService studentService = (StudentService) objects.computeIfAbsent("studentService", k -> {
            StudentService ss = new StudentService();
            ss.setStudentDAO(getStudentDAO());
            return ss;
        });
        return studentService;
    }

}
