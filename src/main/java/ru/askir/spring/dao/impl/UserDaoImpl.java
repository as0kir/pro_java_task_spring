package ru.askir.spring.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.askir.spring.dao.UserDao;
import ru.askir.spring.dto.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private final DataSource dataSource;
    private final Connection connection;

    public UserDaoImpl(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.connection = dataSource.getConnection();
    }

    @Override
    public User create(String username) {
        String sql = "INSERT INTO users (username) VALUES (?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, username);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if(keys.next()) {
                        Long id = keys.getLong(1);
                        logger.debug("Вставлена новая запись с id " + id);
                        return new User(id, username);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE users SET username=? WHERE id=?";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.username());
            statement.setLong(2, user.id());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                logger.debug("Обновлена запись с id " + user.id());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                logger.debug("Запись успешно удалена");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public User findById(Long id) {
        String sql = "SELECT * FROM users WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()){
                String username = result.getString("username");
                return new User(id, username);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";

        List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                Long id = result.getLong("id");
                String username = result.getString("username");
                userList.add(new User(id, username));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }
}
