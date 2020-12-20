package com.company.persistence;

import com.company.model.User;
import com.company.persistence.mappers.UserMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Collection;

@RegisterRowMapper(UserMapper.class)
public interface UserDAO {
    String SELECT_QUERY = "SELECT id, postalcode, firstname, middlename, lastname, address, email, town FROM consumer";
    String INSERT_QUERY = "INSERT INTO consumer(postalcode, firstname, middlename, lastname, address, email, town) " +
            "VALUES (:postalCode, :firstName, :middleName, :lastName, :address, :email, :town)";
    String DELETE_QUERY = "DELETE FROM consumer WHERE id = :id";
    String UPDATE_QUERY = "UPDATE consumer SET postalcode = :postalCode, firstname = :firstName, middlename = :middleName, " +
            "lastname = :lastName, address = :address, email = :email, town = :town";

    @SqlQuery(SELECT_QUERY + " WHERE id = :id")
    User getUser(@Bind("id") Integer id);

    @SqlQuery(SELECT_QUERY)
    Collection<User> getAllUsers();

    @SqlUpdate(UPDATE_QUERY)
    void updateUser(@BindBean User user);

    @SqlUpdate(DELETE_QUERY)
    boolean deleteUser(@Bind("id") Integer id);

    @GetGeneratedKeys
    @SqlUpdate(INSERT_QUERY)
    int createUser(@BindBean User user);
}
