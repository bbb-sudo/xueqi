package dao;
import java.util.List;

import model.*;
public interface EnrollmentDAO {
    Enrollment getEnrollmentById(int id);
    boolean addEnrollment(Enrollment enrollment);
    boolean deleteEnrollment(int id);
    boolean updateScore(int id, float score);
    List<Enrollment> getEnrollmentsByStudent(String studentId);
    List<Enrollment> getEnrollmentsByCourse(int courseId);
}