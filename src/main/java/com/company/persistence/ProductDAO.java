package com.company.persistence;

import com.company.model.Product;
import com.company.persistence.mappers.ProductMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RegisterRowMapper(ProductMapper.class)
public class ProductDAO implements Dao<Product> {
    private Connection connection;

    public ProductDAO(Connection connection) { this.connection = connection; }

    public ProductDAO() {

    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO product (name, description, price, imagepath)\n" +
                "VALUES (?, ?, ?, ?)";
    }

    @Override
    public String getDeleteQuery() { return "DELETE FROM product WHERE id = ?"; }

    @Override
    public String getUpdateQuery() {
        return "UPDATE product\n" +
                "SET name = ?, " +
                "description = ?, " +
                "price = ?, " +
                "imagepath = ?";
    }

    @Override
    public String getFindByIDQuery() {
        return "SELECT id,\n" +
                "name, \n" +
                "description, \n" +
                "price, \n" +
                "imagepath\n" +
                "FROM product\n" +
                "WHERE id = ?";
    }

    @Override
    public String getAllQuery() {
        return "SELECT id,\n" +
                "name, \n" +
                "description, \n" +
                "price, \n" +
                "imagepath\n" +
                "FROM product";
    }

    @Override
    public Product insert(Product product) {
        try (PreparedStatement statement = this.connection.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getImagePath());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
            product.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Product findByID(int id) throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(getFindByIDQuery())) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Product(
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getDouble("price"),
                    resultSet.getString("imagepath"),
                    resultSet.getInt("id"));
        }
    }

    @Override
    public Product createEntity(ResultSet resultSet) throws SQLException {
        Product product = new Product(
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDouble("price"),
                resultSet.getString("imagepath"),
                resultSet.getInt("id"));
        return product;
    }

    @Override
    public List<Product> getAll() {
        ArrayList<Product> products = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement(getAllQuery())) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(createEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void update(Product product) {
        try (PreparedStatement statement = this.connection.prepareStatement(getUpdateQuery())) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setString(4, product.getImagePath());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(getDeleteQuery())) {
            statement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
