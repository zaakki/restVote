package ru.restaurantvoting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurantvoting.model.Vote;
import ru.restaurantvoting.util.exception.ExpiredDateTimeException;
import ru.restaurantvoting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.restaurantvoting.data.MenuTestData.MENU_ID_2;
import static ru.restaurantvoting.data.MenuTestData.MENU_ID_3;
import static ru.restaurantvoting.data.UserTestData.ADMIN_ID;
import static ru.restaurantvoting.data.UserTestData.USER_ID;
import static ru.restaurantvoting.data.VoteTestData.*;
import static ru.restaurantvoting.util.ValidationUtil.EXPIRED_TIME;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void create() throws Exception {
        Vote newVote = new Vote(null, LocalDate.now());
        Vote created = service.create(newVote.getDate(), ADMIN_ID, MENU_ID_3);
        newVote.setId(created.getId());
        assertMatch(created, newVote);
        assertMatch(service.getAll(), VOTE_1, VOTE_2, VOTE_3, newVote);
    }

    @Test
    void get() throws Exception {
        Vote vote = service.get(VOTE_ID_1, USER_ID, MENU_ID_2);
        assertMatch(vote, VOTE_1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(1, 1, 1));
    }

    @Test
    void getForUserAndDate() throws Exception {
        Vote vote = service.getForUserAndDate(USER_ID, LocalDate.of(2020, 11, 20));
        assertMatch(vote, VOTE_1);
    }

    @Test
    void update() throws Exception {
        Vote updated = new Vote(VOTE_3);
        LocalTime time = LocalTime.now();
        if(time.isBefore(EXPIRED_TIME)) {
            updated.setDate(LocalDate.now());
            service.update(new Vote(updated), USER_ID, MENU_ID_3);
            assertMatch(service.get(VOTE_ID_3, USER_ID, MENU_ID_3), updated);
        }
        else{

            assertThrows(ExpiredDateTimeException.class, () ->
                    service.update(VOTE_3,USER_ID,MENU_ID_3));
        }
    }

    @Test
    void getAll() throws Exception {
        List<Vote> all = service.getAll();
        assertMatch(all, VOTE_1, VOTE_2, VOTE_3);
    }

    @Test
    void getAllByDate() throws Exception {
        List<Vote> all = service.getAllByDate(LocalDate.now());
        assertMatch(all, VOTE_3);
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createWithException() throws Exception {
        validateRootCause(() -> service.create(null, USER_ID, MENU_ID_2), IllegalArgumentException.class);
    }
}