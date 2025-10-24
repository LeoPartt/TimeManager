package eu.epitech.t_dev_700.services.exceptions;

import eu.epitech.t_dev_700.models.ErrorModels;
import eu.epitech.t_dev_700.utils.HasDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.AuthenticationException;

@Schema(description = "Deleted user", example = "Deleted user")
public final class DeletedUser extends AuthenticationException implements HasDetails<ErrorModels.InvalidCredentialsDetail> {

    private final ErrorModels.InvalidCredentialsDetail details;

    public DeletedUser(String username) {
        super("Deleted user");
        this.details = new ErrorModels.InvalidCredentialsDetail(username);
    }

    @Override
    public ErrorModels.InvalidCredentialsDetail details() {
        return details;
    }
}
