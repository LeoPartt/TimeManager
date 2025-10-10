package eu.epitech.t_dev_700.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Consumer;

public final class InvalidClocking extends ResponseStatusException implements Consumer<Object>, Runnable {

    public InvalidClocking() {
        super(HttpStatus.BAD_REQUEST, "Invalid clocking");
    }

    @Override
    public void accept(Object o) {
        throw this;
    }

    @Override
    public void run() {
        throw this;
    }
}
