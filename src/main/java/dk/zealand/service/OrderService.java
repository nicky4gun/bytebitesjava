package dk.zealand.service;

import dk.zealand.model.Dish;
import dk.zealand.model.Order;
import dk.zealand.model.OrderStatus;
import dk.zealand.repository.DishRepository;
import dk.zealand.repository.OrderRepository;
import dk.zealand.service.exception.OrderCreationException;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class OrderService {

    private static final int MAX_ORDERS = 10;

    private final DishRepository dishRepository;
    private final OrderRepository orderRepository;
    private final AtomicInteger orderSequence = new AtomicInteger(0);

    public OrderService(DishRepository dishRepository, OrderRepository orderRepository) {
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
    }

    public List<Dish> getAvailableDishes() {
        return dishRepository.findAll();
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void validateDishChoice(int choice) {
        if (dishRepository.findByMenuNumber(choice).isEmpty()) {
            throw new OrderCreationException("Ugyldig ret. Vælg en af de tre retter.");
        }
    }

    public void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new OrderCreationException("Ugyldigt antal. Antal skal være større end 0.");
        }
    }

    public Order createOrder(int dishChoice, int quantity) {
        validateDishChoice(dishChoice);
        validateQuantity(quantity);

        if (orderRepository.count() >= MAX_ORDERS) {
            throw new OrderCreationException("Der kan højst gemmes ti bestillinger.");
        }

        Dish dish = dishRepository.findByMenuNumber(dishChoice)
                .orElseThrow(() -> new OrderCreationException("Ugyldig ret. Vælg en af de tre retter."));
        Order order = new Order(nextOrderId(), dish, quantity, OrderStatus.MODTAGET);
        orderRepository.save(order);
        return order;
    }

    private String nextOrderId() {
        return "ORD-" + orderSequence.incrementAndGet();
    }
}
