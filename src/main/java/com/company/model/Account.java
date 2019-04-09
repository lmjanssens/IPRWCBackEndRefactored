package com.company.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;

public class Account implements Principal {
    @NotEmpty
    private String name;

    @NotEmpty
    @Length(min = 8)
    private String password;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
