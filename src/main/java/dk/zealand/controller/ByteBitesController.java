package dk.zealand.controller;

import dk.zealand.model.Dish;
import dk.zealand.model.Order;
import dk.zealand.service.OrderService;
import dk.zealand.service.exception.OrderCreationException;

import java.util.List;
import java.util.Scanner;

public final class ByteBitesController {

    private final OrderService orderService;

    public ByteBitesController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void run(Scanner scanner) {
        boolean running = true;

        System.out.println("ByteBites – festivalens foodtruck");

        while (running && scanner.hasNextLine()) {
            showMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> showDishes();
                case "2" -> createOrder(scanner);
                case "0" -> running = false;
                default -> System.out.println("Ugyldigt valg. Vælg 0, 1 eller 2.");
            }
        }

        System.out.println("Programmet er afsluttet.");
    }

    private void showMenu() {
        System.out.println();
        System.out.println("1. Vis retter");
        System.out.println("2. Opret bestilling");
        System.out.println("0. Afslut");
        System.out.print("Vælg: ");
    }

    private void showDishes() {
        List<Dish> dishes = orderService.getAvailableDishes();
        System.out.println("Retter:");

        for (int i = 0; i < dishes.size(); i++) {
            Dish dish = dishes.get(i);
            System.out.printf("%d. %s - %d kr.%n", i + 1, dish.name(), dish.price());
        }
    }

    private void createOrder(Scanner scanner) {
        Integer dishChoice = promptForDishChoice(scanner);
        if (dishChoice == null) {
            return;
        }

        Integer quantity = promptForPositiveQuantity(scanner);
        if (quantity == null) {
            return;
        }

        try {
            Order order = orderService.createOrder(dishChoice, quantity);
            System.out.println("Bestilling oprettet:");
            System.out.println(order.toDisplayString());
            System.out.println("Antal bestillinger i alt: " + orderService.getOrders().size());
        } catch (OrderCreationException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private Integer promptForDishChoice(Scanner scanner) {
        while (scanner.hasNextLine()) {
            System.out.print("Vælg ret (1-3): ");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                orderService.validateDishChoice(choice);
                return choice;
            } catch (NumberFormatException exception) {
                System.out.println("Ugyldigt valg. Indtast 1, 2 eller 3.");
            } catch (OrderCreationException exception) {
                System.out.println(exception.getMessage());
            }
        }

        return null;
    }

    private Integer promptForPositiveQuantity(Scanner scanner) {
        while (scanner.hasNextLine()) {
            System.out.print("Antal: ");
            String input = scanner.nextLine().trim();

            try {
                int quantity = Integer.parseInt(input);
                orderService.validateQuantity(quantity);
                return quantity;
            } catch (NumberFormatException exception) {
                System.out.println("Ugyldigt antal. Indtast et helt tal over 0.");
            } catch (OrderCreationException exception) {
                System.out.println(exception.getMessage());
            }
        }

        return null;
    }
}
