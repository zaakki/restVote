package ru.restaurantvoting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurantvoting.model.Dish;
import ru.restaurantvoting.service.DishService;
import ru.restaurantvoting.to.DishTo;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.restaurantvoting.util.DishUtil.dishFromTo;
import static ru.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.restaurantvoting.util.ValidationUtil.checkNew;


@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/rest/dishes";
    public static final String MENUS_URL = "/menus/";

    @Autowired
    private DishService service;

    @GetMapping
    public List<Dish> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping("/{id}" + MENUS_URL + "{menuId}")
    public Dish get(@PathVariable int id, @PathVariable int menuId) {
        log.info("get {} for menuId={}", id, menuId);
        return service.get(id, menuId);
    }

    @PostMapping(value = MENUS_URL + "{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Validated @RequestBody DishTo dishTo, @PathVariable int menuId) {
        log.info("create {} for menuId={}", dishTo, menuId);
        checkNew(dishTo);
        Dish created = service.create(dishFromTo(dishTo), menuId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}/menus/{menuId}")
                .buildAndExpand(menuId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}" + MENUS_URL + "{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int menuId) {
        log.info("delete {} for menuId={}", id, menuId);
        service.delete(id, menuId);
    }

    @PutMapping(value = "/{id}" + MENUS_URL + "{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Validated @RequestBody DishTo dishTo, @PathVariable int id, @PathVariable int menuId) {
        log.info("update {} with id={} for menuId={}", dishTo, id, menuId);
        assureIdConsistent(dishTo, id);
        service.update(dishFromTo(dishTo), menuId);
    }

    @GetMapping("/by")
    public List<Dish> findByDate(@RequestParam LocalDate date) {
        log.info("findByDate {}", date);
        return service.findByDate(date);
    }

    @GetMapping(MENUS_URL + "{menuId}")
    public List<Dish> findByMenu(@PathVariable int menuId) {
        log.info("findByMenu {}", menuId);
        return service.findByMenu(menuId);
    }
}