package com.company.service;

import com.company.model.User;
import com.company.persistence.UserDAO;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Singleton
public class AuthenticationService {
    private final UserDAO userDAO;
    private static final String UNAUTHORIZED_MESSAGE = "Invalid username and/or password.";
    private static final int LOG_ROUNDS = 11;

    @Inject
    public AuthenticationService(UserDAO userDAO) { this.userDAO = userDAO; }

    public User authenticateUser(String user, String password) throws SQLException {
        User subject = userDAO.getByUsername(user);
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