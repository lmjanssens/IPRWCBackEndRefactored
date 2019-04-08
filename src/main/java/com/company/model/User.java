package com.company.model;

import com.company.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;

public class User implements Principal {
    private Integer id = -1;

    @NotEmpty
    @Length(min = 6, max = 6)
    @JsonView(View.Public.class)
    private String postalCode;

    @NotEmpty
    @Length(max = 50)
    @JsonView(View.Public.class)
    private String address;

    @NotEmpty
    @Length(max = 30)
    @JsonView(View.Public.class)
    private String firstName;

    private String middleName;

    @NotEmpty
    @Length(max = 30)
    @JsonView(View.Public.class)
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

    public User(int id, String postalCode,
                String firstName, String middleName, String lastName,
                String username, String password, String address, String email) {
        this.id = id;
        this.postalCode = postalCode;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
    }

    public User() {

    }

    @JsonProperty
    @Override
    @JsonIgnore
    public String getName() {
        return null;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @JsonProperty
    public String getAddress() { return address; }

    @JsonProperty
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty
    public String getFirstName() { return firstName; }

    @JsonProperty
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty
    public String getMiddleName() {
        return middleName;
    }

    @JsonProperty
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty
    public String getEmail() { return email; }

    @JsonProperty
    public void setEmail(String email) { this.email = email; }

    @JsonProperty
    public String getUsername() {
        return username;
    }

    @JsonProperty
    public void setUsername(String username) {
        this.username = username;
    }
}
