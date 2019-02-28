package com.company.resource;

import com.codahale.metrics.annotation.Timed;
import com.company.View;
import com.company.model.Product;
import com.company.service.ProductService;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Collection;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Singleton
@Path("/producten")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ProductResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);
    private final ProductService productService;

    @Inject
    public ProductResource(ProductService productService) { this.productService = productService; }

    @GET
    @JsonView(View.Public.class)
//    @RolesAllowed("student")
    @Timed
    public Collection<Product> retrieveAll() {
        LOGGER.info("testing");
        return productService.getAll();
    }
}
