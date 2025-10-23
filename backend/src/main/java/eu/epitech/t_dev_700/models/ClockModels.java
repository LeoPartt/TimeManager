package eu.epitech.t_dev_700.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public class ClockModels {

    @Schema(description = "Clock action type - IN for clock-in, OUT for clock-out")
    public enum ClockAction {
        @Schema(description = "Clock in - Start of work period")
        IN,
        @Schema(description = "Clock out - End of work period")
        OUT
    }

    @Schema(description = "Request body for recording a clock event")
    public record PostClockRequest(
            @Schema(description = "Clock action type (IN or OUT)", example = "IN")
            @NotNull ClockModels.ClockAction io,

            @Schema(description = "Timestamp of the clock event", example = "2024-01-15T10:30:00Z")
            @NotNull OffsetDateTime timestamp
    ) {}

}
