package dao;
import java.util.List;

import model.*;
public interface CourseDAO {
    Course getCourseById(int courseId);
    boolean addCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourse(int courseId);
    List<Course> getAllCourses();
    List<Course> getCoursesByTeacher(String teacherId);
}