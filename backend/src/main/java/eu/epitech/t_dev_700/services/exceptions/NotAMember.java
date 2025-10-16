package eu.epitech.t_dev_700.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

public final class NotAMember extends ResponseStatusException implements Runnable {

    public NotAMember(Long userId, Long teamId) {
        super(HttpStatus.BAD_REQUEST, "%d is not a member of %d".formatted(userId, teamId));
    }

    @Override
    public void run() {
        throw this;
    }
}
