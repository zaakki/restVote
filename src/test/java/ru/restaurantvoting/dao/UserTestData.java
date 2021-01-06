package ru.restaurantvoting.dao;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.restaurantvoting.model.Role;
import ru.restaurantvoting.model.User;
import ru.restaurantvoting.web.json.JsonUtil;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static ru.restaurantvoting.TestUtil.readFromJsonMvcResult;
import static ru.restaurantvoting.TestUtil.readListFromJsonMvcResult;
import static ru.restaurantvoting.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@email.com", "user_password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@email.com", "admin_password", Role.ADMIN, Role.USER);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "votes", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "votes", "password").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(User... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, User.class), List.of(expected));
    }

    public static ResultMatcher contentJson(User expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, User.class), expected);
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}