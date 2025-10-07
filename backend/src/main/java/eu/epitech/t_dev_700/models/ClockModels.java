package eu.epitech.t_dev_700.models;

import jakarta.validation.constraints.NotNull;

public class ClockModels {

    public enum ClockAction {
        IN,
        OUT
    }

    public record PostClockRequest(
            @NotNull ClockModels.ClockAction io
    ) {}

}
