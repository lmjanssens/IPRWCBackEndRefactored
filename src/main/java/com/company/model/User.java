package com.company.model;

import com.company.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;

public class User implements Principal {
    @NotEmpty
    @Length(min = 8)
    @JsonView(View.Public.class)
    private String password;

    @NotEmpty
    @Email
    @JsonView(View.Public.class)
    private String email;

    @NotEmpty
    @JsonView(View.Public.class)
    private String username;

    @JsonView(View.Public.class)
    private String[] roles;

    @Override
    public String getName() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public boolean hasRole(String roleName) {
        if (roles != null) {
            for (String role : roles) {
                if (roleName.equals(role)) {
                    return true;
                }
            }
        }
        return false;
    }
}
