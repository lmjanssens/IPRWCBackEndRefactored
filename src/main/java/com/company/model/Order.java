package com.company.model;

import com.company.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;

public class Order implements Principal {
    @NotEmpty
    @JsonView(View.Public.class)
    private int orderId;

    @NotEmpty
    @JsonView(View.Public.class)
    private int consumerId;

    @NotEmpty
    @JsonView(View.Public.class)
    private int productId;

    @NotEmpty
    @JsonView(View.Public.class)
    private String productName;

    public Order(int orderId, int consumerId, int productId, String productName) {
        this.orderId = orderId;
        this.consumerId = consumerId;
        this.productId = productId;
        this.productName = productName;
    }

    // We need an empty constructor to serve as default constructor for Hibernate
    public Order() {

    }

    @Override
    public String getName() {
        return null;
    }
}
