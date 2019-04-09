package com.company.service;

import com.company.model.Product;
import com.company.persistence.ProductDAO;
import com.google.inject.Inject;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.Collection;

public class ProductService extends BaseService<Product> implements Service<Product> {
    private final ProductDAO productDAO;

    @Inject
    public ProductService(ProductDAO productDAO) { this.productDAO = productDAO; }

    @Override
    public Collection<Product> getAll() {
        Collection<Product> products = productDAO.list();
        return products;
    }

    @Override
    public Product get(Integer id) {
        Product product = productDAO.get(id);
        return requireResult(product);
    }

    @Override
    public Product add(Product product) { return errorIfEmpty(get(productDAO.add(product))); }

    @Override
    public Response delete(Integer id) {
        if (!productDAO.removeById(id)) {
            throw new NotFoundException("Product niet gevonden");
        }
        return Response.ok().build();
    }

    @Override
    public Product update(Product product) {
        productDAO.update(product);
        return product;
    }
}
