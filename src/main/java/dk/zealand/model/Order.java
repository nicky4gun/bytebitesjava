package dk.zealand.model;

public record Order(String id, Dish dish, int quantity, OrderStatus status) {

    public Order {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Bestilling skal have et id.");
        }
        if (dish == null) {
            throw new IllegalArgumentException("Bestilling skal have en ret.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Antal skal være større end 0.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Bestilling skal have en status.");
        }
    }

    public String toDisplayString() {
        return "%s | %s x%d | status %s".formatted(id, dish.name(), quantity, status);
    }
}
