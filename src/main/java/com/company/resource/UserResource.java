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

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Singleton
@Path("/gebruikers")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class UserResource {
    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @JsonView(View.Public.class)
//    @RolesAllowed("student")
    @Timed
    public Collection<User> retrieveAll() {
        LOGGER.info("testing");
        return userService.getAll();
    }

    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
//    @RolesAllowed("student")
    public User retrieve(@PathParam("id") IntParam id) {
        LOGGER.info("Retrieving contact with id: {}", id);
        return userService.get(id.get());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(@Valid User user) {
        userService.insert(user);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("GUEST")
    public void update(@PathParam("id") int id, @Auth User authenticator, @Valid User user) {
        user.setId(authenticator.getId());
        userService.update(user);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public void delete(@PathParam("id") int id) {
        userService.delete(id);
    }

    @GET
    @Path("/me")
    @JsonView(View.Private.class)
    public User authenticate(@Auth User authenticator) {
        return authenticator;
    }
}
