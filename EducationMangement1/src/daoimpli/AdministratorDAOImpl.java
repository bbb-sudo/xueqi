package daoimpli;

import dao.AdministratorDAO;
import model.Administrator;
import tool.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorDAOImpl implements AdministratorDAO {
    @Override
    public Administrator getAdministratorById(int id) {
        String sql = "SELECT * FROM administrator WHERE adminId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Administrator admin = new Administrator();
                admin.setId(rs.getInt("adminId"));
                admin.setId(rs.getInt("userId"));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addAdministrator(Administrator admin) {
        String sql = "INSERT INTO administrator (userId) VALUES (?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, admin.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAdministrator(int id) {
        String sql = "DELETE FROM administrator WHERE adminId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}