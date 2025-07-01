package ServiceImp;

import dao.EnrollmentDAO;
import model.Enrollment;
import service.GradeService;

import java.util.ArrayList;
import java.util.List;

public class GradeServiceImpl implements GradeService {
    private EnrollmentDAO enrollmentDAO;

    public GradeServiceImpl(EnrollmentDAO enrollmentDAO) {
        this.enrollmentDAO = enrollmentDAO;
    }

    @Override
    public void enterGrade(int enrollmentId, float score, String teacherId) {
        Enrollment enrollment = enrollmentDAO.getEnrollmentById(enrollmentId);
        if (enrollment != null && enrollment.getScore() == 0) { // 假设0为未录入
            enrollmentDAO.updateScore(enrollmentId, score);
        }
        // 否则不能修改分数，需管理员退回
    }

    @Override
    public void returnGradeForEdit(int enrollmentId) {
        enrollmentDAO.updateScore(enrollmentId, 0); // 管理员退回成绩，重置为0
    }

    @Override
    public float getStudentScore(String studentId, int courseId) {
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentsByStudent(studentId);
        for (Enrollment e : enrollments) {
            if (e.getCourseId() == courseId) return e.getScore();
        }
        return 0;
    }

    @Override
    public List<Enrollment> getStudentAllGrades(String studentId) {
        return enrollmentDAO.getEnrollmentsByStudent(studentId);
    }

    @Override
    public List<Float> analyzeCourseGrades(int courseId) {
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentsByCourse(courseId);
        List<Float> grades = new ArrayList<>();
        for (Enrollment e : enrollments) {
            grades.add(e.getScore());
        }
        return grades;
    }

    @Override
    public void sendParentReport(String studentId, String contact) {
        // 实际应调用邮件/短信发送工具，这里仅演示
        List<Enrollment> grades = enrollmentDAO.getEnrollmentsByStudent(studentId);
        System.out.println("向" + contact + "发送成绩报告: " + grades);
    }
}
