package eu.epitech.t_dev_700.models;

import eu.epitech.t_dev_700.utils.HasDetails;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

public class ErrorModels {

    @Schema(description = "Error response (see RFC 9457)")
    @ExternalDocumentation(url = "https://datatracker.ietf.org/doc/html/rfc9457")
    public static record ErrorResponse(

            @Schema(description = "Reason phrase of the status code")
            @NotNull
            String title,

            @Schema(description = "Status code of the error")
            @NotNull
            int status,

            @Schema(description = "Human-readable explanation specific to this occurrence of the problem")
            @NotNull
            String detail,

            @Schema(description = "The route on which the problem occurred")
            @NotNull
            String instance,

            @Schema(description = "An object containing specific information about the problem")
            @NotNull
            Object details,

            @Schema(description = "Timestamp at which the problem occurred")
            @NotNull
            Instant at
    ) {

        private static String getRequestUri(WebRequest request) {
            return ((ServletWebRequest) request).getRequest().getRequestURI();
        }

        public ErrorResponse(HttpStatus status, String detail, WebRequest request, Object details) {
            this(
                    status.getReasonPhrase(),
                    status.value(),
                    detail,
                    getRequestUri(request),
                    details,
                    Instant.now()
            );
        }

        public ErrorResponse(HttpStatus status, String detail, WebRequest request) {
            this(status, detail, request, new Object());
        }

        public ErrorResponse(HttpStatus status, Exception ex, WebRequest request) {
            this(status, ex.getMessage(), request, (ex instanceof HasDetails<?> hasDetails)?hasDetails.details():new Object());
        }

        public ResponseEntity<Object> toResponse() {
            return ResponseEntity.status(this.status).body(this);
        }
    }

    @Schema()
    public record InvalidClockingDetail (
            @Schema(description = "The expected clocking action for the current state", example = "OUT")
            @NotNull
            ClockModels.ClockAction expected
    ) {}

    @Schema()
    public record ResourceNotFoundDetail (
            @Schema(description = "The internal name of the requested resource", example = "user")
            @NotNull
            String resource,

            @Schema(description = "The id of the requested resource", example = "OUT")
            @NotNull
            Long id
    ) {}

    @Schema()
    public record InvalidCredentialsDetail(
            @Schema(description = "The username provided on authentication", example = "johndoe01")
            @NotNull
            String username
    ) {}

    @Schema()
    public record NotAMemberDetail(
            @Schema(description = "The id of the user", example = "1")
            @NotNull
            long userId,

            @Schema(description = "The id of the team", example = "1")
            @NotNull
            long teamId
    ) {}
}
