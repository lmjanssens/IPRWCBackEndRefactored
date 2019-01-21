package com.company.service;

import com.company.model.User;
import com.company.persistence.UserDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentials;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.Optional;

@Singleton
public class AuthenticationService implements Authenticator<BasicCredentials, User>, Authorizer<User> {
    private final UserDAO userDAO;

    @Inject
    public AuthenticationService(UserDAO userDAO) { this.userDAO = userDAO; }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        User user = null;
        try {
            user = userDAO.getByEmail(credentials.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user != null && user.getPassword().equals(credentials.getPassword())) {
            return Optional.of(user);
        }

        return Optional.empty();
    }

    @Override
    public boolean authorize(User user, String roleName) { return user.hasRole(roleName); }
}