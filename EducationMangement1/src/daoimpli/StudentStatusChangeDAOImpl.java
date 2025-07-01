package daoimpli;

import dao.StudentStatusChangeDAO;
import model.StudentStatusChange;
import tool.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentStatusChangeDAOImpl implements StudentStatusChangeDAO {
    @Override
    public StudentStatusChange getStatusChangeById(int id) {
        String sql = "SELECT * FROM studentstatuschange WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                StudentStatusChange change = new StudentStatusChange();
                change.setId(rs.getInt("id"));
                change.setStudentId(rs.getString("studentId"));
                change.setChangeType(rs.getString("changeType"));
                change.setDescription(rs.getString("description"));

                return change;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addStatusChange(StudentStatusChange change) {
        String sql = "INSERT INTO studentstatuschange (studentId, changeType, description, changeDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, change.getStudentId());
            ps.setString(2, change.getChangeType());
            ps.setString(3, change.getDescription());

            boolean success = ps.executeUpdate() > 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                change.setId(rs.getInt(1));
            }
            return success;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<StudentStatusChange> getStatusChangesByStudent(String studentId) {
        List<StudentStatusChange> list = new ArrayList<>();
        String sql = "SELECT * FROM studentstatuschange WHERE studentId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentStatusChange change = new StudentStatusChange();
                change.setId(rs.getInt("id"));
                change.setStudentId(rs.getString("studentId"));
                change.setChangeType(rs.getString("changeType"));
                change.setDescription(rs.getString("description"));
                list.add(change);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}