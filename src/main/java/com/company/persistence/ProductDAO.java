package com.company.persistence;

import com.company.model.Product;
import com.company.persistence.mappers.ProductMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Collection;

@RegisterRowMapper(ProductMapper.class)
public interface ProductDAO {
    String SELECT_QUERY = "SELECT name, description, price, imagepath, id FROM product";
    String INSERT_QUERY = "INSERT INTO product(name, description, price, imagepath) " +
            "VALUES (:name, :description, :price, :imagePath)";
    String DELETE_QUERY = "DELETE FROM product WHERE id = :id";
    String UPDATE_QUERY = "UPDATE product SET name = :name, description = :description, price = :price, imagepath = :imagePath";

    @SqlQuery(SELECT_QUERY + " WHERE id = :id")
    Product get(@Bind("id") Integer id);

    @SqlQuery(SELECT_QUERY)
    Collection<Product> list();

    @SqlUpdate(UPDATE_QUERY)
    void update(@BindBean Product user);

    @SqlUpdate(DELETE_QUERY)
    boolean removeById(@Bind("id") Integer id);

    @GetGeneratedKeys
    @SqlUpdate(INSERT_QUERY)
    int add(@BindBean Product user);
}
