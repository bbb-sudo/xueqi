package ServiceImp;

import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import model.Course;
import model.Enrollment;
import model.Student;
import service.CourseService;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseDAO courseDAO;
    private EnrollmentDAO enrollmentDAO;
    private StudentDAO studentDAO;

    public CourseServiceImpl(CourseDAO courseDAO, EnrollmentDAO enrollmentDAO, StudentDAO studentDAO) {
        this.courseDAO = courseDAO;
        this.enrollmentDAO = enrollmentDAO;
        this.studentDAO = studentDAO;
    }

    // 管理员功能
    @Override
    public void addCourse(Course course) {
        courseDAO.addCourse(course);
    }

    @Override
    public void deleteCourse(int courseId) {
        courseDAO.deleteCourse(courseId);
    }

    @Override
    public void updateCourse(Course course) {
        courseDAO.updateCourse(course);
    }

    // 教师功能
    @Override
    public void teacherOpenCourse(Course course) {
        courseDAO.addCourse(course);
    }

    @Override
    public void teacherUpdateCourse(Course course) {
        courseDAO.updateCourse(course);
    }

    @Override
    public List<Student> getCourseMembers(int courseId) {
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentsByCourse(courseId);
        List<Student> members = new ArrayList<>();
        for (Enrollment e : enrollments) {
            Student s = studentDAO.getStudentById(e.getStudentId());
            if (s != null) members.add(s);
        }
        return members;
    }

    @Override
    public void addStudentToCourse(int courseId, String studentId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setCourseId(courseId);
        enrollment.setStudentId(studentId);
        enrollmentDAO.addEnrollment(enrollment);
    }

    @Override
    public void removeStudentFromCourse(int courseId, String studentId) {
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentsByCourse(courseId);
        for (Enrollment e : enrollments) {
            if (e.getStudentId().equals(studentId)) {
                enrollmentDAO.deleteEnrollment(e.getId());
                break;
            }
        }
    }

    // 学生功能
    @Override
    public void studentSelectCourse(String studentId, int courseId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setCourseId(courseId);
        enrollment.setStudentId(studentId);
        enrollmentDAO.addEnrollment(enrollment);
    }

    @Override
    public void studentDropCourse(String studentId, int courseId) {
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentsByStudent(studentId);
        for (Enrollment e : enrollments) {
            if (e.getCourseId() == courseId) {
                enrollmentDAO.deleteEnrollment(e.getId());
                break;
            }
        }
    }

    @Override
    public List<Course> getStudentCourses(String studentId) {
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentsByStudent(studentId);
        List<Course> courses = new ArrayList<>();
        for (Enrollment e : enrollments) {
            Course c = courseDAO.getCourseById(e.getCourseId());
            if (c != null) courses.add(c);
        }
        return courses;
    }

    @Override
    public Course getCourseInfo(int courseId) {
        return courseDAO.getCourseById(courseId);
    }
}