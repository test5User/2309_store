package by.itclass.model.dao;

import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import static by.itclass.constants.AppConst.*;

public class UserDao {
    private static final String SELECT_USER = "SELECT id,name,email FROM user WHERE login = ? AND password = ?";
    private static final String INSERT_USER = "INSERT INTO user(name, email, login, password) values (?, ?, ?, ?)";
    private static final String CHECK_USER = "SELECT id FROM user WHERE login = ?";
    private static final String CHANGE_PASS = "UPDATE user SET password = ? WHERE id = ?";

    public User selectUser(String login, String password) {
        try (var cn = ConnectionManager.getConnection();
            var ps = cn.prepareStatement(SELECT_USER)) {
            ps.setString(1, login);
            ps.setString(2, password);
            var rs = ps.executeQuery();
            if (rs.next()) {
                var id = rs.getInt("id");
                var name = rs.getString("name");
                var email = rs.getString("email");
                return new User(id, name, email, login, password.toCharArray());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertUser(Map<String, String[]> params) {
        try (var cn = ConnectionManager.getConnection();
            var psInsert = cn.prepareStatement(INSERT_USER);
            var psCheck = cn.prepareStatement(CHECK_USER)){
            psCheck.setString(1, params.get(LOGIN_PARAM)[0]);
            if (isAccessible(psCheck)) {
                psInsert.setString(1, params.get(NAME_PARAM)[0]);
                psInsert.setString(2, params.get(EMAIL_PARAM)[0]);
                psInsert.setString(3, params.get(LOGIN_PARAM)[0]);
                psInsert.setString(4, params.get(PASS_PARAM)[0]);
                return psInsert.executeUpdate() == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isAccessible(PreparedStatement ps) throws SQLException {
        return !ps.executeQuery().next();
    }

    public boolean updateUser(int id, String password) {
        try (var cn = ConnectionManager.getConnection();
            var ps = cn.prepareStatement(CHANGE_PASS)){
            ps.setInt(2, id);
            ps.setString(1, password);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
