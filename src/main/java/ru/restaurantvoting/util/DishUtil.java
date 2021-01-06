package ru.restaurantvoting.util;


import ru.restaurantvoting.model.Dish;
import ru.restaurantvoting.to.DishTo;

public class DishUtil {

    private DishUtil() {
    }

    public static Dish dishFromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(), dishTo.getName(), dishTo.getPrice());
    }
}