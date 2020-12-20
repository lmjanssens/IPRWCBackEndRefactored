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
    String SELECT_QUERY = "SELECT name, description, price, imagepath, id, ean, brand, detaileddescription, shippingcost FROM product";
    String INSERT_QUERY = "INSERT INTO product(name, description, price, imagepath, ean, brand, detaileddescription, shippingcost) " +
            "VALUES (:name, :description, :price, :imagePath, :ean, :brand, :detailedDescription, :shippingCost)";
    String DELETE_QUERY = "DELETE FROM product WHERE id = :id";
    String UPDATE_QUERY = "UPDATE product SET name = :name, description = :description, price = :price, imagepath = :imagePath, " +
            "ean = :ean, brand = :brand, detailddescription = :detailedDescription, shippingcost = :shippingCost";

    @SqlQuery(SELECT_QUERY + " WHERE id = :id")
    Product getProduct(@Bind("id") Integer id);

    @SqlQuery(SELECT_QUERY)
    Collection<Product> getAllProducts();

    @SqlUpdate(UPDATE_QUERY)
    void updateProduct(@BindBean Product order);

    @SqlUpdate(DELETE_QUERY)
    boolean deleteProduct(@Bind("id") Integer id);

    @GetGeneratedKeys
    @SqlUpdate(INSERT_QUERY)
    int createProduct(@BindBean Product product);
}
