package com.company.authentication;

import com.company.model.Account;
import com.company.service.AuthenticationService;
import com.google.inject.Inject;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class AppAuthenticator implements Authenticator<BasicCredentials, Account> {
    private final AuthenticationService authenticationService;

    @Inject
    public AppAuthenticator(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Optional<Account> authenticate(BasicCredentials credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        Account account = authenticationService.authenticateAccount(username, password);
        return Optional.of(account);
    }
}
