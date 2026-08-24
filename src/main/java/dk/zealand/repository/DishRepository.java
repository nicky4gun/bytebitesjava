package dk.zealand.repository;

import dk.zealand.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishRepository {

    List<Dish> findAll();

    Optional<Dish> findByMenuNumber(int menuNumber);
}
