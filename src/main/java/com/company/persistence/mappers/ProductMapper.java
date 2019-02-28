package com.company.persistence.mappers;

import com.company.model.Product;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product map(ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Product(
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDouble("price"),
                resultSet.getString("imagepath"),
                resultSet.getInt("id")
        );
    }
}
