package com.company.resource;

import com.codahale.metrics.annotation.Timed;
import com.company.View;
import com.company.model.User;
import com.company.service.UserService;
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
public final class UserResource {
    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Timed
    public Collection<User> retrieveAll() {
        LOGGER.info("Retrieving all contacts.");
        return userService.getAll();
    }

    @GET
    @Path("/{id}")
    public User retrieve(@PathParam("id") IntParam id) {
        LOGGER.info("Retrieving contact with id: {}", id);
        return userService.get(id.get());
    }

    @POST
    @Timed
    public User create(@Valid User user) { return userService.add(user); }

    @PUT
    @Path("/{id}")
    public User update(@PathParam("id") IntParam id, @Auth User authenticator, @Valid User user) {
        return userService.update(user);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") IntParam id) {
        return userService.delete(id.get());
    }

    @GET
    @Path("/me")
    @JsonView(View.Private.class)
    public User authenticate(@Auth User authenticator) {
        return authenticator;
    }
}
