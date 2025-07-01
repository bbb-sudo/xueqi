package ServiceImp;


import dao.UserDAO;
import dao.CourseDAO;
import model.User;
import service.SystemService;

import java.util.List;

public class SystemServiceImpl implements SystemService {
    private UserDAO userDAO;
    private CourseDAO courseDAO;

    public SystemServiceImpl(UserDAO userDAO, CourseDAO courseDAO) {
        this.userDAO = userDAO;
        this.courseDAO = courseDAO;
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    @Override
    public void freezeUser(int userId) {
        User user = userDAO.getUserById(userId);
        if (user != null) {
            user.setStatus("frozen");
            userDAO.updateUser(user);
        }
    }

    @Override
    public List<User> listUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void showMenu(int userId) {
        User user = userDAO.getUserById(userId);
        if (user == null) return;
        switch (user.getRole()) {
            case "管理员":
                System.out.println("显示管理员菜单");
                break;
            case "教师":
                System.out.println("显示教师菜单");
                break;
            case "学生":
                System.out.println("显示学生菜单");
                break;
            default:
                System.out.println("未知角色");
                break;
        }
    }

    @Override
    public void returnCourseGrade(int courseId) {
        // 实际应有成绩状态管理，这里仅演示
        System.out.println("管理员退回课程成绩，课程ID：" + courseId);
    }

    @Override
    public void deleteCourseAndData(int courseId) {
        courseDAO.deleteCourse(courseId);
        // 相关资料删除应在其他DAO实现
        System.out.println("删除课程及相关资料，课程ID：" + courseId);
    }
}