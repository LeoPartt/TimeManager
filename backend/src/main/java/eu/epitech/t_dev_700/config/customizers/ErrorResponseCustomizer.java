package eu.epitech.t_dev_700.config.customizers;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.Optional;

@Configuration
public class ErrorResponseCustomizer {

    public OperationCustomizer customizeErrorSchemas() {
        return (operation, handlerMethod) -> {
            System.out.println(operation.getSummary());

            operation.getResponses().forEach((code, response) -> {
                if (isErrorCode(code)) {

                    Schema<?> schema = new Schema<>()
                            .$ref("#/components/schemas/ErrorResponse");

                    String path = (String) Optional.ofNullable(operation.getExtensions()).orElse(Map.of()).getOrDefault("x-path", "/");

                    schema.setExample(Map.of(
                            "title", HttpStatus.valueOf(Integer.parseInt(code)).getReasonPhrase(),
                            "status", code,
                            "detail", getDefaultMessageFor(code),
                            "instance", path,
                            "details", "{}",
                            "at", "2025-10-17T12:00:00Z"
                    ));

                    schema.setExternalDocs(new ExternalDocumentation().url("https://datatracker.ietf.org/doc/html/rfc9457"));

                    response.content(new Content()
                            .addMediaType("application/json",
                                    new MediaType().schema(schema)));
                }
            });

            return operation;
        };
    }

    private boolean isErrorCode(String code) {
        try {
            int c = Integer.parseInt(code);
            return c >= 400;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String getDefaultMessageFor(String code) {
        return switch (code) {
            case "400" -> "Bad Request - invalid input";
            case "401" -> "Unauthorized - missing or invalid token";
            case "403" -> "Forbidden - insufficient permissions";
            case "404" -> "Not Found - resource missing";
            case "500" -> "Internal Server Error";
            default -> "Error";
        };
    }
}
