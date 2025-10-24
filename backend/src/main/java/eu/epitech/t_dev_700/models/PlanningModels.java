package eu.epitech.t_dev_700.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.epitech.t_dev_700.entities.PlanningEntity;
import eu.epitech.t_dev_700.models.constraints.NullableNotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalTime;

public class PlanningModels {

    public interface Planning {

        PlanningEntity.DayOfWeek dayOfWeek();

        LocalTime startTime();

        LocalTime endTime();

    }

    @Schema(description = "Planning information model")
    public record PlanningResponse(
            @Schema(description = "Unique planning entry identifier", example = "1")
            Long id,

            @Schema(description = "Unique identifier of the user that owns this entry", example = "1")
            Long userId,

            @Schema(description = "Day of week of this entry", example = "1")
            @JsonFormat(shape = JsonFormat.Shape.NUMBER)
            PlanningEntity.DayOfWeek dayOfWeek,

            @Schema(description = "Start time of this entry", example = "09:30")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
            LocalTime startTime,

            @Schema(description = "End time of this entry", example = "09:30")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
            LocalTime endTime
    ) implements Planning, HasId {
    }

    @Schema(description = "Request body for creating a new planning entry")
    public record PostPlanningRequest(
            @Schema(description = "Day of week of this entry", example = "1")
            @JsonFormat(shape = JsonFormat.Shape.NUMBER)
            @NotBlank
            PlanningEntity.DayOfWeek dayOfWeek,

            @Schema(description = "Start time of this entry", example = "09:30")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
            @NotBlank
            LocalTime startTime,

            @Schema(description = "End time of this entry", example = "09:30")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
            @NotBlank
            LocalTime endTime
    ) implements Planning {
    }

    @Schema(description = "Request body for completely replacing a planning entry (PUT)")
    public record PutPlanningRequest(
            @Schema(description = "Day of week of this entry", example = "1")
            @JsonFormat(shape = JsonFormat.Shape.NUMBER)
            @NotBlank
            PlanningEntity.DayOfWeek dayOfWeek,

            @Schema(description = "Start time of this entry", example = "09:30")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
            @NotBlank
            LocalTime startTime,

            @Schema(description = "End time of this entry", example = "09:30")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
            @NotBlank
            LocalTime endTime
    ) implements Planning {
    }

    @Schema(description = "Request body for partially updating a planning entry (PATCH)")
    public record PatchPlanningRequest(
            @Schema(description = "Day of week of this entry", example = "1")
            @JsonFormat(shape = JsonFormat.Shape.NUMBER)
            @NullableNotBlank
            PlanningEntity.DayOfWeek dayOfWeek,

            @Schema(description = "Start time of this entry", example = "09:30")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
            @NullableNotBlank
            LocalTime startTime,

            @Schema(description = "End time of this entry", example = "09:30")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
            @NullableNotBlank
            LocalTime endTime
    ) implements Planning {
    }

}
