package eu.epitech.t_dev_700.services.exceptions;

import eu.epitech.t_dev_700.models.ClockModels;
import eu.epitech.t_dev_700.utils.HasDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public final class InvalidClocking extends IllegalStateException implements Consumer<Object>, Runnable, HasDetails {

    private final Map<String, Object> details = new HashMap<>();

    public InvalidClocking(ClockModels.ClockAction expectedAction) {
        super("Invalid clocking");
        this.details.put("expected", expectedAction);
    }

    @Override
    public void accept(Object o) {
        throw this;
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
