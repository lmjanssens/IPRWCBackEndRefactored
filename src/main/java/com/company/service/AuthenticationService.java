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
    public AuthenticationService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account authenticateAccount(String username, String password) {
        LOGGER.info("Authenticating account...");
        Account accountToAuthenticate = accountDAO.getAccount(username);

        this.throwForbiddenExceptionErrorIfAccountToAuthenticateIsNullOrPasswordIsIncorrect(accountToAuthenticate, password);
        LOGGER.info("Account successfully authenticated.");

        return accountToAuthenticate;
    }

    private void throwForbiddenExceptionErrorIfAccountToAuthenticateIsNullOrPasswordIsIncorrect(Account accountToAuthenticate, String password) {
        if (accountToAuthenticate == null || !BCrypt.checkpw(password, accountToAuthenticate.getPassword())) {
            LOGGER.info("Account not successfully authenticated. Sending unauthorized message.");

            throw new ForbiddenException(
                    UNAUTHORIZED_MESSAGE,
                    Response
                            .status(Response.Status.FORBIDDEN)
                            .header("WWW-Authenticate", "Basic realm=\"IPRWC\", charset=\"UTF-8\"")
                            .build()
            );
        }
    }
}