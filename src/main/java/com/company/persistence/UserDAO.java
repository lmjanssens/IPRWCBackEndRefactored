package com.company.persistence;

import com.company.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final List<User> users;

    public UserDAO() {
        User student = new User();
        student.setUsername("student");
        student.setPassword("iprwcstudent");
        student.setRoles(new String[] { "NORMAL" });

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("iprwcadmin");
        admin.setRoles(new String[] { "NORMAL", "ADMIN"});

        users = new ArrayList<>();
        users.add(student);
        users.add(admin);
    }

    public List<User> getAll(){
        return users;
    }

    public User get(int id) {
        try {
            return users.get(id);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    public void add(User user) {
        users.add(user);
    }

    public void update(User user, int id) {
        users.set(id, user);
    }

    public void delete(int id) {
        users.remove(id);
    }
}
