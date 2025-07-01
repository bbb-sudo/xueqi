package daoimpli;

import dao.StudentAwardOrPunishDAO;
import model.StudentAwardOrPunish;
import tool.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentAwardOrPunishDAOImpl implements StudentAwardOrPunishDAO {
    @Override
    public StudentAwardOrPunish getAwardOrPunishById(int id) {
        String sql = "SELECT * FROM studentawardorpunish WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                StudentAwardOrPunish ap = new StudentAwardOrPunish();
                ap.setId(rs.getInt("id"));
                ap.setStudentId(rs.getString("studentId"));
                ap.setType(rs.getString("type"));
                ap.setTitle(rs.getString("title"));
                ap.setDescription(rs.getString("description"));
                ap.setDate(rs.getDate("date"));
                return ap;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addAwardOrPunish(StudentAwardOrPunish record) {
        String sql = "INSERT INTO studentawardorpunish (studentId, type, title, description, date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, record.getStudentId());
            ps.setString(2, record.getType());
            ps.setString(3, record.getTitle());
            ps.setString(4, record.getDescription());
            boolean success = ps.executeUpdate() > 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                record.setId(rs.getInt(1));
            }
            return success;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<StudentAwardOrPunish> getAwardOrPunishByStudent(String studentId) {
        List<StudentAwardOrPunish> list = new ArrayList<>();
        String sql = "SELECT * FROM studentawardorpunish WHERE studentId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentAwardOrPunish ap = new StudentAwardOrPunish();
                ap.setId(rs.getInt("id"));
                ap.setStudentId(rs.getString("studentId"));
                ap.setType(rs.getString("type"));
                ap.setTitle(rs.getString("title"));
                ap.setDescription(rs.getString("description"));
                ap.setDate(rs.getDate("date"));
                list.add(ap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}