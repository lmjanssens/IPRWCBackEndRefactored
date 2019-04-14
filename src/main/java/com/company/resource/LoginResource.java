package com.company.resource;

import com.company.model.Account;
import com.google.inject.Singleton;
import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public final class LoginResource {
    @GET
    public Account getUser(@Auth Account authenticatedAccount) {
        return authenticatedAccount;
    }
}
