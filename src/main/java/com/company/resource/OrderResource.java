package com.company.resource;

import com.codahale.metrics.annotation.Timed;
import com.company.model.Order;
import com.company.model.User;
import com.company.service.OrderService;
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
@Path("/bestellingen")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class OrderResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderResource.class);
    private final OrderService orderService;

    @Inject
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @GET
    @Timed
    public Collection<Order> getAllOrders() {
        LOGGER.info("Retrieving orders.");
        return orderService.getAll();
    }

    @GET
    @Path("/{id}")
    public Order getOrderById(@PathParam("id") IntParam id) {
        LOGGER.info("Retrieving order with id: {}", id);
        return orderService.get(id.get());
    }

    @POST
    @Timed
    public Order postOrder(Order order) {
        return orderService.create(order);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@Auth User authenticatedUser, @PathParam("id") IntParam intParam) {
        LOGGER.info("Deleting order with id: {}", intParam);

        return orderService.delete(intParam.get());
    }
}
