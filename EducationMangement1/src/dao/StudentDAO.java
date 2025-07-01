package dao;
import java.util.List;

import model.*;
public interface StudentDAO {
    Student getStudentById(String studentId);
    boolean addStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(String studentId);
    List<Student> getAllStudents();
}