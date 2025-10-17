package eu.epitech.t_dev_700.models;

import eu.epitech.t_dev_700.utils.HasDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.Map;

@Schema(description = "RFC7807 Error response")
public record ErrorModel(

        @Schema(description = "Reason phrase of the status code", example = "Unprocessable Entity")
        String title,

        @Schema(description = "Status code of the error", example = "422")
        int status,

        @Schema(description = "Human-readable explanation specific to this occurrence of the problem", example = "Validation failed")
        String detail,

        @Schema(description = "The route on which the problem occurred", example = "/users")
        String instance,

        @Schema(description = "An object containing specific information about the problem", example = "{\"lastName\": \"must not be empty\"}")
        Map<String, Object> details,

        @Schema(description = "Timestamp at which the problem occurred", example = "2025-10-17T09:21:47.507792200Z")
        Instant at
) {

    private static String getRequestUri(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    public ErrorModel(HttpStatus status, String detail, WebRequest request, Map<String, Object> details) {
        this(
                status.getReasonPhrase(),
                status.value(),
                detail,
                getRequestUri(request),
                details,
                Instant.now()
        );
    }

    public ErrorModel(HttpStatus status, String detail, WebRequest request) {
        this(status, detail, request, null);
    }

    public ErrorModel(HttpStatus status, Exception ex, WebRequest request) {
        this(status, ex.getMessage(), request, (ex instanceof HasDetails hasDetails)?hasDetails.details():null);
    }

    public ResponseEntity<Object> toResponse() {
        return ResponseEntity.status(this.status).body(this);
    }
}
