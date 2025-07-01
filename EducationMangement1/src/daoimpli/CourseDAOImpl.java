package daoimpli;

import dao.CourseDAO;
import model.Course;
import tool.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM course WHERE courseId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Course c = new Course();
                c.setCourseId(rs.getInt("courseId"));
                c.setName(rs.getString("name"));
                c.setCredit(rs.getFloat("credit"));
                c.setPeriod(rs.getInt("period"));
                c.setProperty(rs.getString("property"));
                c.setDescription(rs.getString("description"));
                c.setTeacherId(rs.getString("teacherId"));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addCourse(Course course) {
        String sql = "INSERT INTO course (name, credit, period, property, description, teacherId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, course.getName());
            ps.setFloat(2, course.getCredit());
            ps.setInt(3, course.getPeriod());
            ps.setString(4, course.getProperty());
            ps.setString(5, course.getDescription());
            ps.setString(6, course.getTeacherId());
            boolean success = ps.executeUpdate() > 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                course.setCourseId(rs.getInt(1));
            }
            return success;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCourse(Course course) {
        String sql = "UPDATE course SET name=?, credit=?, period=?, property=?, description=?, teacherId=? WHERE courseId=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getName());
            ps.setFloat(2, course.getCredit());
            ps.setInt(3, course.getPeriod());
            ps.setString(4, course.getProperty());
            ps.setString(5, course.getDescription());
            ps.setString(6, course.getTeacherId());
            ps.setInt(7, course.getCourseId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCourse(int courseId) {
        String sql = "DELETE FROM course WHERE courseId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM course";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Course c = new Course();
                c.setCourseId(rs.getInt("courseId"));
                c.setName(rs.getString("name"));
                c.setCredit(rs.getFloat("credit"));
                c.setPeriod(rs.getInt("period"));
                c.setProperty(rs.getString("property"));
                c.setDescription(rs.getString("description"));
                c.setTeacherId(rs.getString("teacherId"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Course> getCoursesByTeacher(String teacherId) {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM course WHERE teacherId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, teacherId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCourseId(rs.getInt("courseId"));
                c.setName(rs.getString("name"));
                c.setCredit(rs.getFloat("credit"));
                c.setPeriod(rs.getInt("period"));
                c.setProperty(rs.getString("property"));
                c.setDescription(rs.getString("description"));
                c.setTeacherId(rs.getString("teacherId"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}