package ServiceImp;


import dao.UserDAO;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User login(String username, String password) {
        User user = userDAO.getUserByName(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public void register(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void updatePassword(int userId, String newPassword) {
        User user = userDAO.getUserById(userId);
        if (user != null) {
            user.setPassword(newPassword);
            userDAO.updateUser(user);
        }
    }

    @Override
    public void updateUserInfo(User user) {
        userDAO.updateUser(user);
    }
}
