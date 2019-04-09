package com.company.service;

import com.company.model.Account;
import com.company.persistence.AccountDAO;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Response;

@Singleton
public class AuthenticationService {
    private final AccountDAO accountDAO;
    private static final String UNAUTHORIZED_MESSAGE = "Invalid username and/or password.";

    @Inject
    public AuthenticationService(AccountDAO accountDAO) { this.accountDAO = accountDAO; }

    public Account authenticateUser(String user, String password) {
        Account subject = accountDAO.get(user);
        if (subject == null || !BCrypt.checkpw(password, subject.getPassword())) {
            throw new ForbiddenException(
                    UNAUTHORIZED_MESSAGE,
                    Response
                            .status(Response.Status.FORBIDDEN)
                            .header("WWW-Authenticate", "Basic realm=\"IPRWC\", charset=\"UTF-8\"")
                            .build()
            );
        }
        return subject;
    }
}