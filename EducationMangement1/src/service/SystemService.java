package service;

import model.User;
import java.util.List;

public interface SystemService {
    void addUser(User user);
    void deleteUser(int userId);
    void freezeUser(int userId);
    List<User> listUsers();
    void showMenu(int userId);
    void returnCourseGrade(int courseId); // 退回成绩
    void deleteCourseAndData(int courseId); // 删除课程及相关信息
}