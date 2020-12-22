package com.company.persistence.mappers;

import com.company.model.Consumer;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsumerMapper implements RowMapper<Consumer> {

    @Override
    public Consumer map(ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Consumer(
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
