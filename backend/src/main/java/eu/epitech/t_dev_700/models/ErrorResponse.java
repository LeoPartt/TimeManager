package eu.epitech.t_dev_700.models;

import eu.epitech.t_dev_700.utils.HasDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.Map;

@Schema(description = "Error response (see RFC 9457)", contentMediaType = "application/json")
public record ErrorResponse(

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
        Map<String, Object> details,

        @Schema(description = "Timestamp at which the problem occurred")
        @NotNull
        Instant at
) {

    private static String getRequestUri(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    public ErrorResponse(HttpStatus status, String detail, WebRequest request, Map<String, Object> details) {
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
        this(status, detail, request, null);
    }

    public ErrorResponse(HttpStatus status, Exception ex, WebRequest request) {
        this(status, ex.getMessage(), request, (ex instanceof HasDetails hasDetails)?hasDetails.details():null);
    }

    public ResponseEntity<Object> toResponse() {
        return ResponseEntity.status(this.status).body(this);
    }
}
