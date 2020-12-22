package com.company.resource;

import com.codahale.metrics.annotation.Timed;
import com.company.View;
import com.company.model.Consumer;
import com.company.service.ConsumerService;
import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.params.IntParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Singleton
@Path("/gebruikers")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public final class ConsumerResource {
    private final ConsumerService consumerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerResource.class);

    @Inject
    public ConsumerResource(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GET
    @Timed
    public Collection<Consumer> getAllUsers() {
        LOGGER.info("Getting all users.");
        return consumerService.getAll();
    }

    @GET
    @Path("/{id}")
    public Consumer getUserById(@PathParam("id") IntParam id) {
        LOGGER.info("Getting user with id: {}", id);
        return consumerService.get(id.get());
    }

    @POST
    @Timed
    public Consumer postUser(@Valid Consumer consumer) {
        return consumerService.create(consumer);
    }

    @PUT
    @Path("/{id}")
    public Consumer updateUser(@PathParam("id") IntParam id, @Auth Consumer authenticator, @Valid Consumer consumer) {
        return consumerService.update(consumer);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") IntParam id) {
        return consumerService.delete(id.get());
    }

    @GET
    @Path("/me")
    @JsonView(View.Private.class)
    public Consumer authenticateUser(@Auth Consumer authenticatedConsumer) {
        return authenticatedConsumer;
    }
}
