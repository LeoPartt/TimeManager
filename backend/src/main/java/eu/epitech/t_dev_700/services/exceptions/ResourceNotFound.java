package eu.epitech.t_dev_700.services.exceptions;

import eu.epitech.t_dev_700.utils.HasDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public final class ResourceNotFound extends NoSuchElementException implements Supplier<ResourceNotFound>, HasDetails {

    private final Map<String, Object> details = new HashMap<>();

    public ResourceNotFound(String resource, Long id) {
        super("Resource '%s' #%d not found".formatted(resource, id));
        details.put("resource", resource);
        details.put("id", id);
    }

    @Override
    public ResourceNotFound get() {
        return this;
    }

    @Override
    public Map<String, Object> details() {
        return details;
    }
}
