package dao;
import java.util.List;

import model.*;
public interface StudentRegisterDAO {
    StudentRegister getRegisterById(int id);
    boolean addRegister(StudentRegister register);
    List<StudentRegister> getRegistersByStudent(String studentId);
}