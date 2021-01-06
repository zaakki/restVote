package ru.restaurantvoting.util;


import ru.restaurantvoting.model.Menu;
import ru.restaurantvoting.to.MenuTo;

public class MenuUtil {

    private MenuUtil() {
    }

    public static Menu menuFromTo(MenuTo menuTo) {
        return new Menu(menuTo.getId(), menuTo.getDate());
    }
}