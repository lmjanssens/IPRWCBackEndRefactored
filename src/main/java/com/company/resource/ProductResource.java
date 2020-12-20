package com.company.resource;

import com.codahale.metrics.annotation.Timed;
import com.company.model.Product;
import com.company.model.User;
import com.company.service.ProductService;
import com.google.inject.Inject;
import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.params.IntParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Singleton
@Path("/producten")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ProductResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);
    private final ProductService productService;

    @Inject
    public ProductResource(ProductService productService) { this.productService = productService; }

    @GET
    @Timed
    public Collection<Product> getAllProducts() {
        LOGGER.info("Retrieving products.");
        return productService.getAll();
    }

    @GET
    @Path("/{id}")
    public Product getProductById(@PathParam("id") IntParam id) {
        LOGGER.info("Retrieving product with id: {}", id);
        return productService.get(id.get());
    }

    @POST
    @Timed
    public Product postProduct(Product product) {
        return productService.create(product);
    }

    @DELETE
    @Path("/{productId}")
    public Response deleteProduct(@Auth User authenticatedUser, @PathParam("productId") IntParam intParam) {
        LOGGER.info("Deleting product with id: {}", intParam);

        return productService.delete(intParam.get());
    }
}
