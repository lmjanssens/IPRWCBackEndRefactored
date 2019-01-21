package com.company.model;

import com.company.View;
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
    @JsonView(View.Public.class)
    private int id;

    public Product(String name, String description, double price, String imagepath, int id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagepath;
        this.id = id;
    }

    public Product() {

    }

    @Override
    public String getName() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() { return imagePath; }

    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
