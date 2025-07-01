package service;

import model.Student;
import model.StudentStatusChange;
import model.StudentRegister;
import model.StudentAwardOrPunish;
import java.util.List;

public interface StudentStatusService {
    // 基本信息管理
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(String studentId);
    Student getStudent(String studentId);

    // 学籍变动管理
    void addStatusChange(StudentStatusChange change);
    List<StudentStatusChange> getStatusChanges(String studentId);
    void addAwardOrPunish(StudentAwardOrPunish ap);
    List<StudentAwardOrPunish> getAwardsOrPunishments(String studentId);

    // 注册管理
    void addRegister(StudentRegister reg);
    List<StudentRegister> getRegisters(String studentId);
}
