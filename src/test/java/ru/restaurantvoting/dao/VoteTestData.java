package ru.restaurantvoting.dao;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.restaurantvoting.model.Vote;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.restaurantvoting.TestUtil.readFromJsonMvcResult;
import static ru.restaurantvoting.TestUtil.readListFromJsonMvcResult;
import static ru.restaurantvoting.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final int VOTE_ID_1 = START_SEQ + 16;
    public static final int VOTE_ID_2 = START_SEQ + 17;
    public static final int VOTE_ID_3 = START_SEQ + 18;
//2020-11-20
//2020-11-20
//2021-01-06
    public static final Vote VOTE_1 = new Vote(VOTE_ID_1, LocalDate.of(2020, 11, 20));
    public static final Vote VOTE_2 = new Vote(VOTE_ID_2, LocalDate.of(2020, 11, 20));
    public static final Vote VOTE_3 = new Vote(VOTE_ID_3, LocalDate.now());

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "menu");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user", "menu").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Vote... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, Vote.class), List.of(expected));
    }

    public static ResultMatcher contentJson(List<Vote> expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, Vote.class), expected);
    }

    public static ResultMatcher contentJson(Vote expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, Vote.class), expected);
    }
}