package dao;
import java.util.List;

import model.*;
public interface TeacherDAO {
    Teacher getTeacherById(String teacherId);
    boolean addTeacher(Teacher teacher);
    boolean updateTeacher(Teacher teacher);
    boolean deleteTeacher(String teacherId);
    List<Teacher> getAllTeachers();
}