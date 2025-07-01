package daoimpli;

import dao.MessageDAO;
import model.Message;
import tool.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {
    @Override
    public Message getMessageById(int id) {
        String sql = "SELECT * FROM message WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Message m = new Message();
                m.setId(rs.getInt("id"));
                m.setSenderId(rs.getInt("senderId"));
                m.setReceiverId(rs.getInt("receiverId"));
                m.setContent(rs.getString("content"));
                m.setSendTime(rs.getTimestamp("sendTime"));
                m.setStatus(rs.getString("status"));
                return m;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addMessage(Message message) {
        String sql = "INSERT INTO message (senderId, receiverId, content, sendTime, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, message.getSenderId());
            ps.setInt(2, message.getReceiverId());
            ps.setString(3, message.getContent());
            
            ps.setString(5, message.getStatus());
            boolean success = ps.executeUpdate() > 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                message.setId(rs.getInt(1));
            }
            return success;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMessage(int id) {
        String sql = "DELETE FROM message WHERE id = ?";
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
    public List<Message> getMessagesByReceiver(int receiverId) {
        List<Message> list = new ArrayList<>();
        String sql = "SELECT * FROM message WHERE receiverId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, receiverId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Message m = new Message();
                m.setId(rs.getInt("id"));
                m.setSenderId(rs.getInt("senderId"));
                m.setReceiverId(rs.getInt("receiverId"));
                m.setContent(rs.getString("content"));
                m.setSendTime(rs.getTimestamp("sendTime"));
                m.setStatus(rs.getString("status"));
                list.add(m);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Message> getMessagesBySender(int senderId) {
        List<Message> list = new ArrayList<>();
        String sql = "SELECT * FROM message WHERE senderId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, senderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Message m = new Message();
                m.setId(rs.getInt("id"));
                m.setSenderId(rs.getInt("senderId"));
                m.setReceiverId(rs.getInt("receiverId"));
                m.setContent(rs.getString("content"));
                m.setSendTime(rs.getTimestamp("sendTime"));
                m.setStatus(rs.getString("status"));
                list.add(m);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}