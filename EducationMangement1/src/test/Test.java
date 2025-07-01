package test;


import dao.*;
import daoimpli.*;
import model.*;
import service.*;
import ServiceImp.*;

import java.util.Date;
import java.util.List;

import ServiceImp.CourseServiceImpl;
import ServiceImp.GradeServiceImpl;
import ServiceImp.MessageServiceImpl;
import ServiceImp.SystemServiceImpl;
import ServiceImp.UserServiceImpl;

/**
 * 教务系统综合功能测试文件
 */
public class Test {
    public static void main(String[] args) {
        // 1. 初始化DAO实现类（使用你自己的DBUtil和数据库连接配置）
        UserDAO userDAO = new UserDAOImpl();
        CourseDAO courseDAO = new CourseDAOImpl();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl();
        StudentDAO studentDAO = new StudentDAOImpl();
        MessageDAO messageDAO = new MessageDAOImpl();

        // 2. 初始化Service实现类
        CourseService courseService = new CourseServiceImpl(courseDAO, enrollmentDAO, studentDAO);
        GradeService gradeService = new GradeServiceImpl(enrollmentDAO);
        UserService userService = new UserServiceImpl(userDAO);
        MessageService messageService = new MessageServiceImpl(messageDAO);
        SystemService systemService = new SystemServiceImpl(userDAO, courseDAO);

        // 3. 课程管理功能测试
        System.out.println("===== 课程管理功能测试 =====");
        // 新增课程（管理员或教师）
        Course newCourse = new Course();
        newCourse.setName("数据库原理");
        newCourse.setCredit(3.0f);
        newCourse.setPeriod(48);
        newCourse.setProperty("必修");
        newCourse.setDescription("讲授数据库基本概念与技术");
        newCourse.setTeacherId("T001");
        courseService.addCourse(newCourse);
        System.out.println("添加课程：" + newCourse.getName());

        // 教师编辑课程
        newCourse.setDescription("讲授数据库基本概念与SQL编程");
        courseService.teacherUpdateCourse(newCourse);
        System.out.println("教师修改课程简介：" + newCourse.getDescription());

        // 查询课程信息
        Course info = courseService.getCourseInfo(newCourse.getCourseId());
        System.out.println("课程信息：" + info);

        // 添加学生到课程
        courseService.addStudentToCourse(newCourse.getCourseId(), "S001");
        System.out.println("添加学生S001到课程");

        // 查询课程成员
        List<Student> members = courseService.getCourseMembers(newCourse.getCourseId());
        System.out.println("课程成员：" + members);

        // 移除学生
        courseService.removeStudentFromCourse(newCourse.getCourseId(), "S001");
        System.out.println("从课程中移除S001");

        // 学生选课
        courseService.studentSelectCourse("S002", newCourse.getCourseId());
        System.out.println("学生S002选课");

        // 学生退课
        courseService.studentDropCourse("S002", newCourse.getCourseId());
        System.out.println("学生S002退课");

        // 查询学生课程
        List<Course> myCourses = courseService.getStudentCourses("S002");
        System.out.println("学生S002选修的课程：" + myCourses);

        // 4. 成绩管理功能测试
        System.out.println("\n===== 成绩管理功能测试 =====");
        // 教师录入成绩
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId("S002");
        enrollment.setCourseId(newCourse.getCourseId());
        enrollmentDAO.addEnrollment(enrollment);
        gradeService.enterGrade(enrollment.getId(), 88.5f, "T001");
        System.out.println("录入成绩88.5");

        // 查询成绩
        float score = gradeService.getStudentScore("S002", newCourse.getCourseId());
        System.out.println("查询学生成绩：" + score);

        // 统计分析
        List<Float> gradeList = gradeService.analyzeCourseGrades(newCourse.getCourseId());
        System.out.println("课程成绩统计：" + gradeList);

        // 管理员退回成绩
        gradeService.returnGradeForEdit(enrollment.getId());
        System.out.println("管理员退回成绩");

        // 发送成绩报告
        gradeService.sendParentReport("S002", "parent@example.com");
        System.out.println("发送成绩报告");

        // 5. 用户中心功能测试
        System.out.println("\n===== 用户中心功能测试 =====");
        User user = new User();
        user.setName("testuser");
        user.setPassword("123456");
        user.setRole("学生");
        user.setStatus("active");
        userService.register(user);
        System.out.println("用户注册：" + user.getName());

        // 登录
        User loginUser = userService.login("testuser", "123456");
        System.out.println("用户登录：" + (loginUser != null ? "成功" : "失败"));

        // 修改密码
        userService.updatePassword(user.getId(), "654321");
        System.out.println("修改密码");

        // 修改用户信息
        user.setStatus("frozen");
        userService.updateUserInfo(user);
        System.out.println("修改用户状态为frozen");

        // 6. 消息中心功能测试
        System.out.println("\n===== 消息中心功能测试 =====");
        Message msg = new Message();
        msg.setSenderId(user.getId());
        msg.setReceiverId(2);
        msg.setContent("Hello, this is a test message.");
        msg.setSendTime(new Date());
        msg.setStatus("inbox");
        messageService.sendMessage(msg);
        System.out.println("发送消息：" + msg.getContent());

        // 查询收件箱
        List<Message> inbox = messageService.getInbox(user.getId());
        System.out.println("收件箱消息：" + inbox);

        // 查询发件箱
        List<Message> sent = messageService.getSentBox(user.getId());
        System.out.println("发件箱消息：" + sent);

        // 查询垃圾箱
        List<Message> trash = messageService.getTrash(user.getId());
        System.out.println("垃圾箱消息：" + trash);

        // 搜索消息
        List<Message> searched = messageService.searchMessages(user.getId(), "test");
        System.out.println("搜索包含'test'的消息：" + searched);

        // 删除消息
        messageService.deleteMessage(msg.getId(), user.getId());
        System.out.println("删除消息");

        // 7. 系统管理功能测试
        System.out.println("\n===== 系统管理功能测试 =====");
        // 增加新用户
        User admin = new User();
        admin.setName("admin1");
        admin.setPassword("adminpass");
        admin.setRole("管理员");
        admin.setStatus("active");
        systemService.addUser(admin);
        System.out.println("新增用户：" + admin.getName());

        // 删除用户
        systemService.deleteUser(admin.getId());
        System.out.println("删除用户：" + admin.getName());

        // 冻结用户
        systemService.freezeUser(user.getId());
        System.out.println("冻结用户：" + user.getName());

        // 显示菜单
        systemService.showMenu(user.getId());

        // 退回课程成绩
        systemService.returnCourseGrade(newCourse.getCourseId());
        System.out.println("退回课程成绩");

        // 删除课程及相关资料
        systemService.deleteCourseAndData(newCourse.getCourseId());
        System.out.println("删除课程及相关资料");

        System.out.println("\n===== 所有功能测试完成 =====");
    }
}