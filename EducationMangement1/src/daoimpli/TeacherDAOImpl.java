package daoimpli;

import dao.TeacherDAO;
import model.Teacher;
import tool.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {
    @Override
    public Teacher getTeacherById(String teacherId) {
        String sql = "SELECT * FROM teacher WHERE teacherId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, teacherId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Teacher t = new Teacher();
                t.setTeacherId(rs.getString("teacherId"));
                t.setName(rs.getString("name"));
                t.setGender(rs.getString("gender"));
                t.setTitle(rs.getString("title"));
                t.setCollege(rs.getString("college"));
                t.setUserId(rs.getInt("userId"));
                return t;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        String sql = "INSERT INTO teacher (teacherId, name, gender, title, college, userId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, teacher.getTeacherId());
            ps.setString(2, teacher.getName());
            ps.setString(3, teacher.getGender());
            ps.setString(4, teacher.getTitle());
            ps.setString(5, teacher.getCollege());
            ps.setInt(6, teacher.getUserId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        String sql = "UPDATE teacher SET name=?, gender=?, title=?, college=?, userId=? WHERE teacherId=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, teacher.getName());
            ps.setString(2, teacher.getGender());
            ps.setString(3, teacher.getTitle());
            ps.setString(4, teacher.getCollege());
            ps.setInt(5, teacher.getUserId());
            ps.setString(6, teacher.getTeacherId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTeacher(String teacherId) {
        String sql = "DELETE FROM teacher WHERE teacherId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, teacherId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> list = new ArrayList<>();
        String sql = "SELECT * FROM teacher";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Teacher t = new Teacher();
                t.setTeacherId(rs.getString("teacherId"));
                t.setName(rs.getString("name"));
                t.setGender(rs.getString("gender"));
                t.setTitle(rs.getString("title"));
                t.setCollege(rs.getString("college"));
                t.setUserId(rs.getInt("userId"));
                list.add(t);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}