package eu.epitech.t_dev_700.services.exceptions;

import eu.epitech.t_dev_700.utils.HasDetails;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.HashMap;
import java.util.Map;

public class InvalidCredentials extends BadCredentialsException implements HasDetails {

    private final Map<String, Object> details = new HashMap<>();

    public InvalidCredentials(String msg, String username, Throwable cause) {
        super(msg, cause);
        this.details.put("username", username);
    }

    @Override
    public Map<String, Object> details() {
        return details;
    }
}
