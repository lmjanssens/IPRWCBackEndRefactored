package com.company.persistence;

import com.company.model.Account;
import com.company.persistence.mappers.AccountMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterRowMapper(AccountMapper.class)
public interface AccountDAO {
    String SELECT_QUERY = "SELECT username, password FROM account";

    @SqlQuery(SELECT_QUERY + " WHERE username = :username")
    Account getAccount(@Bind("username") String username);
}
