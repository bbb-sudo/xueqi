package dao;
import java.util.List;

import model.*;
public interface StudentAwardOrPunishDAO {
    StudentAwardOrPunish getAwardOrPunishById(int id);
    boolean addAwardOrPunish(StudentAwardOrPunish record);
    List<StudentAwardOrPunish> getAwardOrPunishByStudent(String studentId);
}