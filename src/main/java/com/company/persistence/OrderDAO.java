package com.company.persistence;

import com.company.model.Order;
import com.company.persistence.mappers.OrderMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Collection;

@RegisterRowMapper(OrderMapper.class)
public interface OrderDAO {
    String SELECT_QUERY = "SELECT consumerid, productid, productname, orderid FROM orders";
    String INSERT_QUERY = "INSERT INTO orders(consumerid, productid, productname) VALUES (:consumerId, :productId, :productName)";
    String DELETE_QUERY = "DELETE FROM orders WHERE orderid = :orderId";
    String UPDATE_QUERY = "UPDATE orders SET productid = :productId, productname = :productName, consumerid = :consumerId";

    @SqlQuery(SELECT_QUERY + " WHERE orderid = :orderId")
    Order getOrder(@Bind("orderId") Integer id);

    @SqlQuery(SELECT_QUERY)
    Collection<Order> getAllOrders();

    @SqlUpdate(UPDATE_QUERY)
    void updateOrder(@BindBean Order order);

    @SqlUpdate(DELETE_QUERY)
    boolean deleteOrder(@Bind("orderId") Integer id);

    @GetGeneratedKeys
    @SqlUpdate(INSERT_QUERY)
    int createOrder(@BindBean Order order);
}
