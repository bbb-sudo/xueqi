package service;

import model.User;

public interface UserService {
    User login(String username, String password);
    void register(User user);
    void updatePassword(int userId, String newPassword);
    void updateUserInfo(User user);
}
