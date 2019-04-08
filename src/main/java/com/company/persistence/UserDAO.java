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
    String SELECT_QUERY = "SELECT id, postalcode, firstname, middlename, lastname, username, password, address, email FROM consumer";
    String INSERT_QUERY = "INSERT INTO consumer(postalcode, firstname, middlename, lastname, username, password, address, email) " +
            "VALUES (:postalCode, :firstName, :middleName, :lastName, :username, :password, :address, :email)";
    String DELETE_QUERY = "DELETE FROM consumer WHERE id = :id";
    String UPDATE_QUERY = "UPDATE consumer SET postalcode = :postalCode, firstname = :firstName, middlename = :middleName, " +
            "lastname = :lastName, username = :username, password = :password, address = :address, email = :email";
    String SELECT_CREDENTIALS = "SELECT username, password FROM consumer WHERE username = :username";

    @SqlQuery(SELECT_CREDENTIALS)
    User verify(@Bind("username") String username);

    @SqlQuery(SELECT_QUERY + " WHERE id = :id")
    User get(@Bind("id") Integer id);

    @SqlQuery(SELECT_QUERY)
    Collection<User> list();

    @SqlUpdate(UPDATE_QUERY)
    void update(@BindBean User user);

    @SqlUpdate(DELETE_QUERY)
    boolean removeById(@Bind("id") Integer id);

    @GetGeneratedKeys
    @SqlUpdate(INSERT_QUERY)
    int add(@BindBean User user);
}
