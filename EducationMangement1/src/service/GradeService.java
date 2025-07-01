package service;

import model.Enrollment;
import java.util.List;

public interface GradeService {
    // 教师录入成绩（只能录入一次）
    void enterGrade(int enrollmentId, float score, String teacherId);

    // 管理员退回成绩
    void returnGradeForEdit(int enrollmentId);

    // 查询成绩
    float getStudentScore(String studentId, int courseId);

    List<Enrollment> getStudentAllGrades(String studentId);

    // 统计分析
    List<Float> analyzeCourseGrades(int courseId);

    // 向家长发送成绩邮件/短信
    void sendParentReport(String studentId, String contact);
}
