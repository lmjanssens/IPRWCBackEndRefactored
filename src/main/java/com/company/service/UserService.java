package com.company.service;

import com.company.database.DatabaseConnection;
import com.company.model.User;
import com.company.persistence.UserDAO;
import com.google.inject.Inject;

import java.sql.SQLException;
import java.util.Collection;

public class UserService extends BaseService<User> implements Service<User> {
    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO) {
        this.userDAO = new UserDAO(DatabaseConnection.getConnection());
    }

    @Override
    public Collection<User> getAll() { return userDAO.getAll(); }

    @Override
    public User get(int id) {
//        try {
//            return requireResult(userDAO.findByID(id));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
        User user = null;
        try {
            user = userDAO.findByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requireResult(user);
    }

    public User add(User user) {
        return errorIfEmpty(get(userDAO.insert(user)));
    }

    @Override
    public User insert(User user) {
        return userDAO.insert(user);
    }

    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);//TODO Add authenticator? Check example API
    }
}
