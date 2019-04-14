package com.company.persistence.mappers;

import com.company.model.Account;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {
    @Override
    public Account map(ResultSet resultSet, StatementContext ctx) throws SQLException {
        return new Account(
                resultSet.getString("username"),
                resultSet.getString("password")
        );
    }
}
