package com.company.persistence.mappers;

import com.company.model.User;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("postalcode"),
                resultSet.getString("firstname"),
                resultSet.getString("middlename"),
                resultSet.getString("lastname"),
                resultSet.getString("address"),
                resultSet.getString("email"),
                resultSet.getString("town")
        );
    }
}
