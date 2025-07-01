package daoimpli;

import dao.EnrollmentDAO;
import model.Enrollment;
import tool.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements EnrollmentDAO {
    @Override
    public Enrollment getEnrollmentById(int id) {
        String sql = "SELECT * FROM enrollment WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Enrollment e = new Enrollment();
                e.setId(rs.getInt("id"));
                e.setStudentId(rs.getString("studentId"));
                e.setCourseId(rs.getInt("courseId"));
                e.setScore(rs.getFloat("score"));
                return e;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO enrollment (studentId, courseId, score) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setFloat(3, enrollment.getScore());
            boolean success = ps.executeUpdate() > 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                enrollment.setId(rs.getInt(1));
            }
            return success;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEnrollment(int id) {
        String sql = "DELETE FROM enrollment WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateScore(int id, float score) {
        String sql = "UPDATE enrollment SET score = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setFloat(1, score);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(String studentId) {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT * FROM enrollment WHERE studentId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Enrollment e = new Enrollment();
                e.setId(rs.getInt("id"));
                e.setStudentId(rs.getString("studentId"));
                e.setCourseId(rs.getInt("courseId"));
                e.setScore(rs.getFloat("score"));
                list.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT * FROM enrollment WHERE courseId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Enrollment e = new Enrollment();
                e.setId(rs.getInt("id"));
                e.setStudentId(rs.getString("studentId"));
                e.setCourseId(rs.getInt("courseId"));
                e.setScore(rs.getFloat("score"));
                list.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}