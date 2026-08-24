package dk.zealand.repository;

import dk.zealand.model.Dish;

import java.util.List;
import java.util.Optional;

public final class InMemoryDishRepository implements DishRepository {

    private static final List<Dish> DISHES = List.of(
            new Dish("Festivalburger", 59),
            new Dish("Sprøde fritter", 35),
            new Dish("Vegansk bowl", 65)
    );

    @Override
    public List<Dish> findAll() {
        return DISHES;
    }

    @Override
    public Optional<Dish> findByMenuNumber(int menuNumber) {
        if (menuNumber < 1 || menuNumber > DISHES.size()) {
            return Optional.empty();
        }

        return Optional.of(DISHES.get(menuNumber - 1));
    }
}
