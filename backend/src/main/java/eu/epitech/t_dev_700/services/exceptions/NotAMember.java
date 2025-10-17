package eu.epitech.t_dev_700.services.exceptions;

import eu.epitech.t_dev_700.utils.HasDetails;

import java.util.HashMap;
import java.util.Map;

public final class NotAMember extends IllegalStateException implements Runnable, HasDetails {

    private final Map<String, Object> details = new HashMap<>();

    public NotAMember(Long userId, Long teamId) {
        super("User #%d is not a member of team #%d".formatted(userId, teamId));
        this.details.put("userId", userId);
        this.details.put("teamId", userId);
    }

    @Override
    public void run() {
        throw this;
    }

    @Override
    public Map<String, Object> details() {
        return details;
    }
}
