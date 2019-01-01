package com.company.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;

public class Product implements Principal {
    @NotEmpty
    @Length(min = 1, max = 100)
    //@JsonView(View.Public.class)
    private String name;

    @NotEmpty
    @Length(max = 255)
    //@JsonView(View.Public.class)
    private String description;

    @NotEmpty
    //@JsonView(View.Public.class)
    private double price;

    @Override
    public String getName() {
        return null;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
