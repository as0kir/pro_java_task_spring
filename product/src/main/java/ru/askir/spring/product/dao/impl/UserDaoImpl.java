package ru.askir.spring.product.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.askir.spring.product.dao.UserDao;
import ru.askir.spring.product.dto.response.UserResponse;
import ru.askir.spring.product.exception.ProductServiceException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private final Connection connection;

    public UserDaoImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public UserResponse create(String username) {
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
                        return new UserResponse(id, username);
                    }
                }
            }
            else
                throw new ProductServiceException("Запись не создана");

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ProductServiceException(ex);
        }
        return null;
    }

    @Override
    public UserResponse update(UserResponse user) {
        String sql = "UPDATE users SET username=? WHERE id=?";

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.username());
            statement.setLong(2, user.id());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                logger.debug("Обновлена запись с id " + user.id());
            }
            else
                throw new ProductServiceException("Не найдена запись для обновления");

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ProductServiceException(ex);
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
            else
                throw new ProductServiceException("Запись не найдена");

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ProductServiceException(ex);
        }
    }

    @Override
    public UserResponse findById(Long id) {
        String sql = "SELECT * FROM users WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()){
                String username = result.getString("username");
                return new UserResponse(id, username);
            }
            else
                throw new ProductServiceException("Запись не найдена");

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ProductServiceException(ex);
        }
    }

    @Override
    public List<UserResponse> findAll() {
        String sql = "SELECT * FROM users";

        List<UserResponse> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                Long id = result.getLong("id");
                String username = result.getString("username");
                userList.add(new UserResponse(id, username));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ProductServiceException(ex);
        }
        return userList;
    }
}
