package eu.epitech.t_dev_700.services.exceptions;

import eu.epitech.t_dev_700.models.ErrorModels;
import eu.epitech.t_dev_700.utils.HasDetails;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

@Schema(example = "Resource 'user' #0 not found")
public final class ResourceNotFound extends NoSuchElementException implements Supplier<ResourceNotFound>, HasDetails<ErrorModels.ResourceNotFoundDetail> {

    private final ErrorModels.ResourceNotFoundDetail details;

    public ResourceNotFound(String resource, Long id) {
        super("Resource '%s' #%d not found".formatted(resource, id));
        details = new ErrorModels.ResourceNotFoundDetail(resource, id);
    }

    @Override
    public ResourceNotFound get() {
        return this;
    }

    @Override
    public ErrorModels.ResourceNotFoundDetail details() {
        return details;
    }
}
