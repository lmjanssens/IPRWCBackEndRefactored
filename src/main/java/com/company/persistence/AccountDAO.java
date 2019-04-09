package com.company.persistence;

import com.company.model.Account;
import com.company.persistence.mappers.AccountMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Collection;

@RegisterRowMapper(AccountMapper.class)
public interface AccountDAO {
    String SELECT_QUERY = "SELECT username, password FROM account";
    String INSERT_QUERY = "INSERT INTO account(username, password) VALUES (:username, :password)";
    String UPDATE_QUERY = "UPDATE account SET password = :password WHERE username = :username";
    String DELETE_QUERY = "DELETE FROM account WHERE username = :username";

    @SqlQuery(SELECT_QUERY + " WHERE username = :username")
    Account get(@Bind("username") String username);

    @SqlQuery(SELECT_QUERY)
    Collection<Account> list();

    @SqlUpdate(UPDATE_QUERY)
    void update(@BindBean Account account);

    @SqlUpdate(DELETE_QUERY)
    boolean remove(@BindBean Account account);

    @GetGeneratedKeys
    @SqlUpdate(INSERT_QUERY)
    int add(@BindBean Account account);
}
