package eu.epitech.t_dev_700.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

public final class ResourceNotFoundException extends ResponseStatusException {
    public static Supplier<ResourceNotFoundException> supply(String resource, Long id) {
        return () -> new ResourceNotFoundException(resource, id);
    }

    public ResourceNotFoundException(String resource, Long id) {
        super(HttpStatus.NOT_FOUND, "%s %d not found".formatted(resource, id));
    }
}
