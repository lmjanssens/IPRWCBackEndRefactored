package com.company.persistence;

import com.company.model.User;
import org.intellij.lang.annotations.Language;

public class UserDAO {

    public UserDAO() {

    }


    @Language("PostgreSQL")
    public String getInsertQuery() {
        return "INSERT INTO consumer (id, postalcode, firstname, middlename, lastname, username, password)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Language("PostgreSQL")
    public String getDeleteQuery() {
        return "DELETE FROM consumer WHERE id = ?";
    }

    @Language("PostgreSQL")
    public String getUpdateQuery() {
        return "UPDATE consumer\n" +
                "SET postalcode = ?, " +
                "firstname = ?, " +
                "middlename = ? ," +
                "lastname = ?, " +
                "username = ?, " +
                "password = ?";
    }

    public void insert(User user) {

    }
}
