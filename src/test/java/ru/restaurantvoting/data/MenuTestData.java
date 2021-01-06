package ru.restaurantvoting.data;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.restaurantvoting.model.Menu;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.restaurantvoting.TestUtil.readFromJsonMvcResult;
import static ru.restaurantvoting.TestUtil.readListFromJsonMvcResult;
import static ru.restaurantvoting.model.AbstractBaseEntity.START_SEQ;

public class MenuTestData {
    public static final int MENU_ID_1 = START_SEQ + 5;
    public static final int MENU_ID_2 = START_SEQ + 6;
    public static final int MENU_ID_3 = START_SEQ + 7;
    public static final int MENU_ID_4 = START_SEQ + 8;
    public static final int MENU_ID_5 = START_SEQ + 9;
//2020-11-19
//2020-11-20
//2021-01-06
//2021-01-06
//2020-06-12
    public static final Menu MENU_1 = new Menu(MENU_ID_1, LocalDate.of(2020, 11, 19));
    public static final Menu MENU_2 = new Menu(MENU_ID_2, LocalDate.of(2020, 11, 20));
    public static final Menu MENU_3 = new Menu(MENU_ID_3, LocalDate.now());
    public static final Menu MENU_4 = new Menu(MENU_ID_4, LocalDate.now());
    public static final Menu MENU_5 = new Menu(MENU_ID_5, LocalDate.of(2020, 6, 12));

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dishes", "restaurant");
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dishes", "restaurant").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Menu... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, Menu.class), List.of(expected));
    }

    public static ResultMatcher contentJson(Menu expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, Menu.class), expected);
    }
}