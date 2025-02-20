package com.company.model;

import com.company.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;

public class Consumer implements Principal {
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
    @Email
    @JsonView(View.Public.class)
    private String email;

    @NotEmpty
    @JsonView(View.Public.class)
    private String town;

    public Consumer(int id, String postalCode, String firstName, String middleName,
                    String lastName, String address, String email, String town) {
        this.id = id;
        this.postalCode = postalCode;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.town = town;
    }

    // We need an empty constructor to serve as default constructor for Hibernate
    public Consumer() {

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
}
