package com.company.persistence;

import com.company.model.User;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Dao<User> {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }


    @Language("PostgreSQL")
    public String getInsertQuery() {
        return "INSERT INTO consumer (postalcode, firstname, middlename, lastname, username, password, address, email)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Language("PostgreSQL")
    public String getDeleteQuery() {
        return "DELETE FROM consumer WHERE id = ?";
    }

    @Language("PostgreSQL")
    public String getUpdateQuery() {
        return "UPDATE consumer\n" +
                "SET postalcode = ?, " +
                "firstname = ?, " +
                "middlename = ?, " +
                "lastname = ?, " +
                "username = ?, " +
                "password = ?, " +
                "address = ?, " +
                "email = ?";
    }

    @Language("PostgreSQL")
    public String getFindByIDQuery() {
        return "SELECT id,\n" +
                "postalcode,\n" +
                "firstname,\n" +
                "middlename,\n" +
                "lastname,\n" +
                "username,\n" +
                "password,\n" +
                "address,\n" +
                "email\n" +
                "FROM consumer\n" +
                "WHERE id = ?";
    }

    @Language("PostgreSQL")
    public String getAllQuery() {
        return "SELECT id,\n" +
                "postalcode,\n" +
                "firstname,\n" +
                "middlename,\n" +
                "lastname,\n" +
                "username,\n" +
                "password,\n" +
                "address,\n" +
                "email\n" +
                "FROM consumer";
    }

    public User insert(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement(getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getPostalCode());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getMiddleName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getUsername());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getAddress());
            statement.setString(8, user.getEmail());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
            user.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void update(User user) {
        try (PreparedStatement statement = this.connection.prepareStatement(getUpdateQuery())) {
            statement.setString(1, user.getPostalCode());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getMiddleName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getUsername());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getAddress());
            statement.setString(8, user.getEmail());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(getDeleteQuery())) {
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findByID(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(getFindByIDQuery())) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new User(resultSet.getInt("id"),
                    resultSet.getString("postalcode"),
                    resultSet.getString("address"),
                    resultSet.getString("firstname"),
                    resultSet.getString("middlename"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("username"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; //TODO: Return null is not supposed to be here, fix it :)
    }

    //TODO: add close statements(connections and queries)
    public List<User> getAll() {
        ArrayList<User> users = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement(getAllQuery())) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(createEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User createEntity(ResultSet resultSet) throws SQLException {
        User user = new User(
                resultSet.getInt("id"),
                resultSet.getString("postalcode"),
                resultSet.getString("address"),
                resultSet.getString("firstname"),
                resultSet.getString("middlename"),
                resultSet.getString("lastname"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("username"));
        return user;
    }
}
