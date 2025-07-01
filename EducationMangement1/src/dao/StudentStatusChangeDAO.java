package dao;
import java.util.List;

import model.*;
public interface StudentStatusChangeDAO {
    StudentStatusChange getStatusChangeById(int id);
    boolean addStatusChange(StudentStatusChange change);
    List<StudentStatusChange> getStatusChangesByStudent(String studentId);
}
