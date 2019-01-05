package com.company.model;

import com.company.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;

public class User implements Principal {
    @NotEmpty
    @JsonView(View.Public.class)
    private int id;

    @NotEmpty
    @Length(min = 6, max = 6)
    private String postalCode;

    @NotEmpty
    @Length(max = 50)
    private String address;

    @NotEmpty
    @Length(max = 30)
    private String firstName;

    @Length(max = 15)
    private String middleName;

    @NotEmpty
    @Length(max = 30)
    private String lastName;

    @NotEmpty
    @Length(min = 8, max = 30)
    @JsonView(View.Private.class)
    private String password;

    @NotEmpty
    @Email
    @JsonView(View.Public.class)
    private String email;

    @NotEmpty
    @Length(max = 30)
    @JsonView(View.Public.class)
    private String username;

    @JsonView(View.Public.class)
    private String[] roles;

    @Override
    @JsonIgnore
    public String getName() {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
