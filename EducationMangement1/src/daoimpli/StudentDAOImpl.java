package daoimpli;

import dao.StudentDAO;
import model.Student;
import tool.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public Student getStudentById(String studentId) {
        String sql = "SELECT * FROM student WHERE studentId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getString("studentId"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setMajor(rs.getString("major"));
                s.setClazz(rs.getString("class"));
                s.setIdcard(rs.getString("idcard"));
                s.setBirthday(rs.getString("birthDate"));
                s.setBirthplace(rs.getString("birthplace"));
                s.setPhoneNumber(rs.getString("phoneNumber"));
                return s;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO student (studentId, name, gender, major, class, idcard, birthDate, birthplace, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getStudentId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getGender());
            ps.setString(4, student.getMajor());
            ps.setString(5, student.getClazz());
            ps.setString(6, student.getIdcard());
            ps.setString(7, student.getBirthday());
            ps.setString(8, student.getBirthplace());
            ps.setString(9, student.getPhoneNumber());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStudent(Student student) {
        String sql = "UPDATE student SET name=?, gender=?, major=?, class=?, idcard=?, birthDate=?, birthplace=?, phoneNumber=? WHERE studentId=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getGender());
            ps.setString(3, student.getMajor());
            ps.setString(4, student.getClazz());
            ps.setString(5, student.getIdcard());
            ps.setString(6, student.getBirthday());
            ps.setString(7, student.getBirthplace());
            ps.setString(8, student.getPhoneNumber());
            ps.setString(9, student.getStudentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStudent(String studentId) {
        String sql = "DELETE FROM student WHERE studentId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getString("studentId"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setMajor(rs.getString("major"));
                s.setClazz(rs.getString("class"));
                s.setIdcard(rs.getString("idcard"));
                s.setBirthday(rs.getString("birthDate"));
                s.setBirthplace(rs.getString("birthplace"));
                s.setPhoneNumber(rs.getString("phoneNumber"));
                list.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}