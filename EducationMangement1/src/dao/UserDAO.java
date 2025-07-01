package dao;

import java.util.List;

import model.User;

public interface UserDAO {
    User getUserById(int id);
    User getUserByName(String name);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    List<User> getAllUsers();
}