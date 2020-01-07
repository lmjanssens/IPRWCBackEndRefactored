package com.company.persistence.mappers;

import com.company.model.Order;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order map(ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Order(
                resultSet.getInt("consumerid"),
                resultSet.getInt("productId"),
                resultSet.getString("productName")
        );
    }
}
