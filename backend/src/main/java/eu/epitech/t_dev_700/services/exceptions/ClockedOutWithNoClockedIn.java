package eu.epitech.t_dev_700.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

public final class ClockedOutWithNoClockedIn extends ResponseStatusException {

    public ClockedOutWithNoClockedIn() {
        super(HttpStatus.BAD_REQUEST, "Not clocked in");
    }
}
