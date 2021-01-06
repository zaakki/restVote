package ru.restaurantvoting.util.exception;

public class ExpiredDateTimeException extends RuntimeException {
    public ExpiredDateTimeException(String message) {
        super(message);
    }
}