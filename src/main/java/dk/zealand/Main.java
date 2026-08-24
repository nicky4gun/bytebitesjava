package dk.zealand;

import dk.zealand.controller.ByteBitesController;
import dk.zealand.repository.InMemoryDishRepository;
import dk.zealand.repository.InMemoryOrderRepository;
import dk.zealand.service.OrderService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ByteBitesController controller = new ByteBitesController(
                new OrderService(
                        new InMemoryDishRepository(),
                        new InMemoryOrderRepository()
                )
        );

        try (Scanner scanner = new Scanner(System.in)) {
            controller.run(scanner);
        }
    }
}
