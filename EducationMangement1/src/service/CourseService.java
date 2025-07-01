package service;
import model.Course;
import model.Student;
import java.util.List;

public interface CourseService {
    // 管理员功能
    void addCourse(Course course); // 增加课程
    void deleteCourse(int courseId); // 删除课程
    void updateCourse(Course course); // 修改课程

    // 教师功能
    void teacherOpenCourse(Course course); // 教师开课（新增课程，teacherId为当前教师）
    void teacherUpdateCourse(Course course); // 教师编辑自己课程
    List<Student> getCourseMembers(int courseId); // 查询课程成员
    void addStudentToCourse(int courseId, String studentId); // 添加成员
    void removeStudentFromCourse(int courseId, String studentId); // 删除成员

    // 学生功能
    void studentSelectCourse(String studentId, int courseId); // 选课
    void studentDropCourse(String studentId, int courseId); // 退课
    List<Course> getStudentCourses(String studentId); // 查询选课
    Course getCourseInfo(int courseId); // 查看课程信息
}