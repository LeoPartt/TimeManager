package eu.epitech.t_dev_700.services.exceptions;

import eu.epitech.t_dev_700.models.ClockModels;
import eu.epitech.t_dev_700.models.ErrorModels;
import eu.epitech.t_dev_700.utils.HasDetails;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.function.Consumer;

@Schema(example = "Invalid clocking")
public final class InvalidClocking extends IllegalStateException implements Consumer<Object>, Runnable, HasDetails<ErrorModels.InvalidClockingDetail> {

    private final ErrorModels.InvalidClockingDetail details;

    public InvalidClocking(ClockModels.ClockAction expectedAction) {
        super("Invalid clocking");
        this.details = new ErrorModels.InvalidClockingDetail(expectedAction);
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
    public ErrorModels.InvalidClockingDetail details() {
        return details;
    }
}
