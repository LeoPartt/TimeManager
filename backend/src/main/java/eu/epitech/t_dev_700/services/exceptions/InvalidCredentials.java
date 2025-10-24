package eu.epitech.t_dev_700.services.exceptions;

import eu.epitech.t_dev_700.models.ErrorModels;
import eu.epitech.t_dev_700.utils.HasDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.authentication.BadCredentialsException;

@Schema(description = "Invalid credentials", example = "Invalid username")
public final class InvalidCredentials extends BadCredentialsException implements HasDetails<ErrorModels.InvalidCredentialsDetail> {

    private final ErrorModels.InvalidCredentialsDetail details;

    public InvalidCredentials(String msg, String username, Throwable cause) {
        super(msg, cause);
        this.details = new ErrorModels.InvalidCredentialsDetail(username);
    }

    @Override
    public ErrorModels.InvalidCredentialsDetail details() {
        return details;
    }
}
