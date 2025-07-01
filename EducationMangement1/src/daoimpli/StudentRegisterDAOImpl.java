package daoimpli;

import dao.StudentRegisterDAO;
import model.StudentRegister;
import tool.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRegisterDAOImpl implements StudentRegisterDAO {
    @Override
    public StudentRegister getRegisterById(int id) {
        String sql = "SELECT * FROM studentregister WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                StudentRegister reg = new StudentRegister();
                reg.setId(rs.getInt("id"));
                reg.setStudentId(rs.getString("studentId"));
                reg.setSemester(rs.getString("semester"));
                reg.setRegistered(rs.getBoolean("registered"));
                reg.setFeePaid(rs.getBoolean("feePaid"));

                return reg;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addRegister(StudentRegister register) {
        String sql = "INSERT INTO studentregister (studentId, semester, registered, feePaid, registerDate) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, register.getStudentId());
            ps.setString(2, register.getSemester());
            ps.setBoolean(3, register.isRegistered());
            ps.setBoolean(4, register.isFeePaid());

            boolean success = ps.executeUpdate() > 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                register.setId(rs.getInt(1));
            }
            return success;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<StudentRegister> getRegistersByStudent(String studentId) {
        List<StudentRegister> list = new ArrayList<>();
        String sql = "SELECT * FROM studentregister WHERE studentId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentRegister reg = new StudentRegister();
                reg.setId(rs.getInt("id"));
                reg.setStudentId(rs.getString("studentId"));
                reg.setSemester(rs.getString("semester"));
                reg.setRegistered(rs.getBoolean("registered"));
                reg.setFeePaid(rs.getBoolean("feePaid"));

                list.add(reg);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}