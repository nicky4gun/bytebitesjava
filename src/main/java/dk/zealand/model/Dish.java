package dk.zealand.model;

public record Dish(String name, int price) {

    public Dish {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ret skal have et navn.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Pris kan ikke være negativ.");
        }
    }
}
