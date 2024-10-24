package ru.askir.spring.product.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.askir.spring.product.dao.ProductDao;
import ru.askir.spring.product.dao.UserDao;
import ru.askir.spring.product.dto.response.ProductResponse;
import ru.askir.spring.product.dto.TypeAccount;
import ru.askir.spring.product.dto.response.UserResponse;
import ru.askir.spring.product.dto.request.ProductRequest;
import ru.askir.spring.product.exception.ProductServiceException;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {
    private final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
    private final Connection connection;
    private final UserDao userDao;

    public ProductDaoImpl(DataSource dataSource, UserDao userDao) throws SQLException {
        this.connection = dataSource.getConnection();
        this.userDao = userDao;
    }

    @Override
    public ProductResponse create(UserResponse user, ProductRequest productRequest) {
        String sql = "INSERT INTO products (user_id, account, balance, type_account) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, user.id());
            statement.setString(2, productRequest.account());
            statement.setBigDecimal(3, productRequest.balance());
            statement.setString(4, productRequest.typeAccount().name());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if(keys.next()) {
                        Long id = keys.getLong(1);
                        logger.debug("Вставлена новая запись с id " + id);
                        return new ProductResponse(id, user, productRequest.account(), productRequest.balance(), productRequest.typeAccount());
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
    public ProductResponse update(UserResponse user, ProductRequest productRequest) {
        String sql = "UPDATE products SET user_id=?, account=?, balance=?, type_account=? WHERE id=?";

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, user.id());
            statement.setString(2, productRequest.account());
            statement.setBigDecimal(3, productRequest.balance());
            statement.setString(4, productRequest.typeAccount().name());
            statement.setLong(5, productRequest.id());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                logger.debug("Обновлена запись с id " + productRequest.id());
            }
            else
                throw new ProductServiceException("Не найдена запись для обновления");

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ProductServiceException(ex);
        }
        return new ProductResponse(productRequest.id(), user, productRequest.account(), productRequest.balance(), productRequest.typeAccount());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM products WHERE id=?";
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
    public ProductResponse findById(Long id) {
        String sql = "SELECT * FROM products WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()){
                Long user_id = result.getLong("user_id");
                String account = result.getString("account");
                BigDecimal balance = result.getBigDecimal("balance");
                String type_balance = result.getString("type_account");

                UserResponse user = userDao.findById(user_id);

                return new ProductResponse(id, user, account, balance, TypeAccount.valueOf(type_balance));
            }
            else
                throw new ProductServiceException("Запись не найдена");

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ProductServiceException(ex);
        }
    }

    @Override
    public List<ProductResponse> findByUserId(Long user_id) {
        String sql = "SELECT * FROM products WHERE user_id=?";

        List<ProductResponse> listProducts = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, user_id);
            ResultSet result = statement.executeQuery();

            while (result.next()){
                Long id = result.getLong("id");
                String account = result.getString("account");
                BigDecimal balance = result.getBigDecimal("balance");
                String type_balance = result.getString("type_account");

                UserResponse user = userDao.findById(user_id);

                listProducts.add(new ProductResponse(id, user, account, balance, TypeAccount.valueOf(type_balance)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ProductServiceException(ex);
        }
        return listProducts;
    }

    @Override
    public List<ProductResponse> findAll() {
        String sql = "SELECT * FROM products";

        List<ProductResponse> listProducts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                Long id = result.getLong("id");
                Long user_id = result.getLong("user_id");
                String account = result.getString("account");
                BigDecimal balance = result.getBigDecimal("balance");
                String type_balance = result.getString("type_account");

                UserResponse user = userDao.findById(user_id);

                listProducts.add(new ProductResponse(id, user, account, balance, TypeAccount.valueOf(type_balance)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ProductServiceException(ex);
        }
        return listProducts;
    }
}
