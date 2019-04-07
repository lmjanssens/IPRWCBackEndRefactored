package com.company.service;

import com.company.model.User;
import com.company.persistence.UserDAO;
import com.google.inject.Inject;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.Collection;

public class UserService extends BaseService<User> implements Service<User> {
    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Collection<User> getAll() {
        Collection<User> users = userDAO.list();
        return users;
    }

    @Override
    public User get(Integer id) {
        User user = userDAO.get(id);
        return requireResult(user);
    }

    @Override
    public User add(User user) {
        return errorIfEmpty(get(userDAO.add(user)));
    }

    @Override
    public Response delete(Integer id) {
        if (!userDAO.removeById(id)) {
            throw new NotFoundException("User niet gevonden.");
        }
        return Response.ok().build();
    }

    @Override
    public User update(User user) {
        userDAO.update(user);
        return user;
    }
}
