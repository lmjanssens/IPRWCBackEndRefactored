package com.company.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;

public class Product implements Principal {
    @NotEmpty
    @Length(min = 1, max = 100)
    private String name;

    private String description;

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
}
