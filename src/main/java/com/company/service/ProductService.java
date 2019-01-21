package com.company.service;

import com.company.model.Product;
import com.company.persistence.ProductDAO;
import com.google.inject.Inject;

import java.util.Collection;

public class ProductService extends BaseService<Product> implements Service<Product> {
    private final ProductDAO productDao;

    @Inject
    public ProductService(ProductDAO productDao) { this.productDao = productDao; }

    @Override
    public Collection<Product> getAll() { return productDao.getAll(); }

    @Override
    public Product get(int id) { return requireResult(productDao.findByID(id)); }

    @Override
    public Product insert(Product product) { return productDao.insert(product); }

    @Override
    public void delete(int id) { productDao.delete(id); }

    @Override
    public void update(Product product) { productDao.update(product); }
}
