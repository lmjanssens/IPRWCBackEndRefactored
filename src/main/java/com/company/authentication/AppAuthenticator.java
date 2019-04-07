package com.company.authentication;

import com.company.model.User;
import com.company.service.AuthenticationService;
import com.google.inject.Inject;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class AppAuthenticator implements Authenticator<BasicCredentials, User> {
    private final AuthenticationService authenticationService;

    @Inject
    public AppAuthenticator(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        User user = authenticationService.authenticateUser(username, password);
        return Optional.of(user);
    }
}
