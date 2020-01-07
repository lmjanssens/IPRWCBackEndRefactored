package com.company.service;

import com.company.model.Order;
import com.company.persistence.OrderDAO;
import com.google.inject.Inject;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.Collection;

public class OrderService extends BaseService<Order> implements Service<Order> {
    private final OrderDAO orderDAO;

    @Inject
    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Collection<Order> getAll() {
        return orderDAO.list();
    }

    @Override
    public Order get(Integer id) {
        Order order = orderDAO.get(id);
        return requireResult(order);
    }

    @Override
    public Order add(Order order) {
        return errorIfEmpty(get(orderDAO.add(order)));
    }

    @Override
    public Response delete(Integer id) {
        if (!orderDAO.removeById(id)) {
            throw new NotFoundException("Bestelling niet gevonden");
        }
        return Response.ok().build();
    }

    @Override
    public Order update(Order order) {
        orderDAO.update(order);
        return order;
    }
}
