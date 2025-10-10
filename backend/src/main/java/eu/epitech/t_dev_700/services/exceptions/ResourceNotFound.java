package eu.epitech.t_dev_700.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

public final class ResourceNotFound extends ResponseStatusException implements Supplier<ResourceNotFound> {

    public ResourceNotFound(String resource, Long id) {
        super(HttpStatus.NOT_FOUND, "%s %d not found".formatted(resource, id));
    }

    @Override
    public ResourceNotFound get() {
        return this;
    }
}
