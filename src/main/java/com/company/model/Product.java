package com.company.model;

import com.company.View;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;

public class Product implements Principal {
    @NotEmpty
    @Length(min = 1, max = 100)
    @JsonView(View.Public.class)
    private String name;

    @NotEmpty
    @Length(max = 255)
    @JsonView(View.Public.class)
    private String description;

    @NotEmpty
    @JsonView(View.Public.class)
    private double price;

    @NotEmpty
    @JsonView(View.Public.class)
    private String imagePath;

    @NotEmpty
    @Length(min = 13, max = 13)
    @JsonView(View.Public.class)
    private String EAN;

    @NotEmpty
    @JsonView(View.Public.class)
    private String brand;

    @NotEmpty
    @JsonView(View.Public.class)
    private String detailedDescription;

    @NotEmpty
    @JsonView(View.Public.class)
    private double shippingCost;

    private Integer id = -1;

    public Product(String name, String description, double price, String imagePath, String EAN, String brand,
                   String detailedDescription, double shippingCost, int id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.EAN = EAN;
        this.brand = brand;
        this.detailedDescription = detailedDescription;
        this.shippingCost = shippingCost;
        this.id = id;
    }

    // We need an empty constructor to serve as default constructor for Hibernate
    public Product() {

    }

    @JsonProperty
    @Override
    public String getName() {
        return name;
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
