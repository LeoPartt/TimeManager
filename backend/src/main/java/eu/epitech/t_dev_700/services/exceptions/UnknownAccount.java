package eu.epitech.t_dev_700.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

public class UnknownAccount extends ResponseStatusException implements Supplier<UnknownAccount> {
    public UnknownAccount() {
        super(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public UnknownAccount get() {
        return this;
    }
}
