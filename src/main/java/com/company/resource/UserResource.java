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
    public Collection<User> getAllUsers() {
        LOGGER.info("Getting all users.");
        return userService.getAll();
    }

    @GET
    @Path("/{id}")
    public User getUserById(@PathParam("id") IntParam id) {
        LOGGER.info("Getting user with id: {}", id);
        return userService.get(id.get());
    }

    @POST
    @Timed
    public User postUser(@Valid User user) {
        return userService.create(user);
    }

    @PUT
    @Path("/{id}")
    public User updateUser(@PathParam("id") IntParam id, @Auth User authenticator, @Valid User user) {
        return userService.update(user);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") IntParam id) {
        return userService.delete(id.get());
    }

    @GET
    @Path("/me")
    @JsonView(View.Private.class)
    public User authenticateUser(@Auth User authenticatedUser) {
        return authenticatedUser;
    }
}
