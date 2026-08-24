package dk.zealand.repository;

import dk.zealand.model.Order;

import java.util.ArrayList;
import java.util.List;

public final class InMemoryOrderRepository implements OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
    }

    @Override
    public List<Order> findAll() {
        return List.copyOf(orders);
    }

    @Override
    public int count() {
        return orders.size();
    }
}
