package eu.epitech.t_dev_700.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Consumer;

public final class InvalidClocking extends ResponseStatusException {

    public InvalidClocking() {
        super(HttpStatus.BAD_REQUEST, "Invalid clocking");
    }

    public static <T> Consumer<T> consumer() {
        return t -> {
            throw new InvalidClocking();
        };
    }

    public static Runnable runnable() {
        return () -> {
            throw new InvalidClocking();
        };
    }
}
