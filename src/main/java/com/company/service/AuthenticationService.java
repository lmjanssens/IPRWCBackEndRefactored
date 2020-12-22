package com.company.service;

import com.company.model.Account;
import com.company.persistence.AccountDAO;
import com.company.resource.ConsumerResource;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Response;

@Singleton
public class AuthenticationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerResource.class);
    private final AccountDAO accountDAO;
    private static final String UNAUTHORIZED_MESSAGE = "Invalid username and/or password.";

    @Inject
    public AuthenticationService(AccountDAO accountDAO) { this.accountDAO = accountDAO; }

    public Account authenticateUser(String user, String password) {
        LOGGER.info("Authenticating user...");
        Account subject = accountDAO.getAccount(user);
        if (subject == null || !BCrypt.checkpw(password, subject.getPassword())) {
            LOGGER.info("User not successfully authenticated. Sending unauthorized message.");
            throw new ForbiddenException(
                    UNAUTHORIZED_MESSAGE,
                    Response
                            .status(Response.Status.FORBIDDEN)
                            .header("WWW-Authenticate", "Basic realm=\"IPRWC\", charset=\"UTF-8\"")
                            .build()
            );
        }
        LOGGER.info("User successfully authenticated.");
        return subject;
    }
}