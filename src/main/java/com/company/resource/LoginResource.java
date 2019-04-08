package com.company.resource;

import com.company.model.User;
import com.google.inject.Singleton;
import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class LoginResource {
    @GET
    public User getUser(@Auth User authenticatedUser) {
        return authenticatedUser;
    }
    //TODO: maak een aparte tabel voor users
}
