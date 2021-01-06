package ru.restaurantvoting.service;

import org.junit.jupiter.api.extension.ExtendWith;
import ru.restaurantvoting.AbstractTest;
import ru.restaurantvoting.TimingExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.restaurantvoting.util.ValidationUtil.getRootCause;

@ExtendWith(TimingExtension.class)
abstract class AbstractServiceTest extends AbstractTest {


    <T extends Throwable> void validateRootCause(Runnable runnable, Class<T> exceptionClass) {
        assertThrows(exceptionClass, () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw getRootCause(e);
            }
        });
    }
}