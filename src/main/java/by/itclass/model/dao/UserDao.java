package by.itclass.model.dao;

import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.User;

public class UserDao {
    private static UserDao dao;

    private UserDao() {
        ConnectionManager.init();
    }

    public static UserDao getInstance() {
        if (dao == null) {
            dao = new UserDao();
        }
        return dao;
    }

    public User selectUser(String login, String password) {
        return null;
    }

    public boolean insertUser(User user) {
        return false;
    }

    public boolean updateUser(int id, String password) {
        return false;
    }
}
