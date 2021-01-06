package ru.restaurantvoting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurantvoting.model.Restaurant;
import ru.restaurantvoting.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.restaurantvoting.data.RestaurantTestData.*;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "new Restaurant");
        Restaurant created = service.create(new Restaurant(newRestaurant));
        newRestaurant.setId(created.getId());
        assertMatch(created, newRestaurant);
        assertMatch(service.getAll(), RESTAURANT_1, RESTAURANT_2, RESTAURANT_3, newRestaurant);
    }

    @Test
    void delete() throws Exception {
        service.delete(RESTAURANT_ID_1);
        assertMatch(service.getAll(), RESTAURANT_2, RESTAURANT_3);
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(1));
    }

    @Test
    void get() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT_ID_1);
        assertMatch(restaurant, RESTAURANT_1);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(1));
    }

    @Test
    void update() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT_1);
        updated.setName("newName");
        service.update(new Restaurant(updated));
        assertMatch(service.get(RESTAURANT_ID_1), updated);
    }

    @Test
    void getAll() throws Exception {
        List<Restaurant> all = service.getAll();
        assertMatch(all, RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);
    }

    @Test
    void findByName() throws Exception {
        Restaurant restaurant = service.findByName(RESTAURANT_1.getName());
        assertMatch(restaurant, RESTAURANT_1);
    }

    @Test
    void findByNameNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.findByName("name"));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createWithException() throws Exception {
        validateRootCause(() -> service.create(new Restaurant(null, "  ")), ConstraintViolationException.class);
    }
}