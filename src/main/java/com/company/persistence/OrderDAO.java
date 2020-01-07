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
    String SELECT_QUERY = "SELECT consumerid, productid, productname FROM orders";
    String INSERT_QUERY = "INSERT INTO orders(consumerid, productid, productname) VALUES (:consumerId, :productId, :productName)";
    String DELETE_QUERY = "DELETE FROM orders WHERE consumerid = :consumerId";
    String UPDATE_QUERY = "UPDATE orders SET productid = :productId, productname = :productName";

    @SqlQuery(SELECT_QUERY + " WHERE consumerid = :consumerid")
    Order get(@Bind("id") Integer id);

    @SqlQuery(SELECT_QUERY)
    Collection<Order> list();

    @SqlUpdate(UPDATE_QUERY)
    void update(@BindBean Order user);

    @SqlUpdate(DELETE_QUERY)
    boolean removeById(@Bind("consumerid") Integer id);

    @GetGeneratedKeys
    @SqlUpdate(INSERT_QUERY)
    int add(@BindBean Order order);
}
