package eu.epitech.t_dev_700.services.exceptions;

import eu.epitech.t_dev_700.models.ErrorModels;
import eu.epitech.t_dev_700.utils.HasDetails;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(example = "User #0 is not a member of team #0")
public final class NotAMember extends IllegalStateException implements Runnable, HasDetails<ErrorModels.NotAMemberDetail> {

    private final ErrorModels.NotAMemberDetail details;

    public NotAMember(Long userId, Long teamId) {
        super("User #%d is not a member of team #%d".formatted(userId, teamId));
        this.details = new ErrorModels.NotAMemberDetail(userId, teamId);
    }

    @Override
    public void run() {
        throw this;
    }

    @Override
    public ErrorModels.NotAMemberDetail details() {
        return details;
    }
}
