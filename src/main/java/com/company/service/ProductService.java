package com.company.service;

import com.company.database.DatabaseConnection;
import com.company.model.Product;
import com.company.persistence.ProductDAO;
import com.google.inject.Inject;

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
    public Product get(int id) {
        try {
            return requireResult(productDao.findByID(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product insert(Product product) { return productDao.insert(product); }

    @Override
    public void delete(int id) { productDao.delete(id); }

    @Override
    public void update(Product product) { productDao.update(product); }
}
