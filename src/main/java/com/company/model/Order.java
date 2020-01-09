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

    public Order() {

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String getName() {
        return null;
    }
}
