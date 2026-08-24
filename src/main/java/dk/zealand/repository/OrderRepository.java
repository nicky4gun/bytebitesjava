package dk.zealand.repository;

import dk.zealand.model.Order;

import java.util.List;

public interface OrderRepository {

    void save(Order order);

    List<Order> findAll();

    int count();
}
