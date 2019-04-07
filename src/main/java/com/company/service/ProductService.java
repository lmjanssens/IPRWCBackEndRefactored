package com.company.service;

import com.company.database.DatabaseConnection;
import com.company.model.Product;
import com.company.persistence.ProductDAO;
import com.google.inject.Inject;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Collection;

public class ProductService extends BaseService<Product> implements Service<Product> {
    private final ProductDAO productDao;

    @Inject
    public ProductService(ProductDAO productDAO) {
        this.productDao = new ProductDAO(DatabaseConnection.getConnection());
    }

    @Override
    public Collection<Product> getAll() { return productDao.getAll(); }

    @Override
    public Product get(Integer id) {
        try {
            return requireResult(productDao.findByID(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product add(Product product) { return productDao.insert(product); }

    @Override
    public Response delete(Integer id) {
        productDao.delete(id);
        return null;
    }

    @Override
    public Product update(Product product) {
        productDao.update(product);
        return product;
    }
}
