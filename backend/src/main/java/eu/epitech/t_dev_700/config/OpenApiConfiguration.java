package eu.epitech.t_dev_700.config;

import eu.epitech.t_dev_700.models.ErrorModels;
import eu.epitech.t_dev_700.utils.HasDetails;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.commons.lang3.RegExUtils;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenApiCustomizer customOpenAPI() {
        return openApi -> {
            openApi.info(new Info()
                            .title("Time Manager API")
                            .description("API for managing time tracking, users, teams, and reports")
                            .version("1.0.0")
                            .license(new License()
                                    .name("MIT License")
                                    .url("https://opensource.org/licenses/MIT")))
                    .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));

            openApi.getComponents()
                    .addSecuritySchemes("bearerAuth",
                            new SecurityScheme()
                                    .type(SecurityScheme.Type.HTTP)
                                    .scheme("bearer")
                                    .bearerFormat("JWT")
                                    .description("JWT authentication token obtained from /auth/login endpoint"));

            // now safely iterate
            if (openApi.getPaths() != null) {
                openApi.getPaths().forEach((path, pathItem) -> {
                    pathItem.readOperationsMap().forEach((method, operation) ->
                            customizeErrorSchemas(operation, method, path)
                    );
                });
            }
        };
    }


    public void customizeErrorSchemas(Operation operation, PathItem.HttpMethod handlerMethod, String path) {

        operation.getResponses().forEach((code, response) -> {
            if (isErrorCode(code)) response.content(new Content().addMediaType("application/json", new MediaType().schema(buildSchema(code, path))));
        });

        Class<?>[] errorsClass = (Class<?>[]) Optional.ofNullable(operation.getExtensions())
                .map(ext -> ext.get("x-errors"))
                .orElse(new Class<?>[]{});
        if (errorsClass.length != 0) operation.getExtensions().remove("x-errors");

        List<String> codes = new ArrayList<>();

        for (Class<?> errorClass : errorsClass) {
            String code = getCodeFromError(errorClass);
            String name = "%s%s".formatted(code, "*".repeat(Collections.frequency(codes, code)));
            codes.add(code);

            Schema<?> schema = buildSchema(code, path);

            io.swagger.v3.oas.annotations.media.Schema annotation = errorClass.getAnnotation(io.swagger.v3.oas.annotations.media.Schema.class);
            String description;
            if (annotation != null) {
                schema.getProperties().get("detail").example(annotation.example());
                description = schema.getDescription();
            } else description = getDescription(code);

            if (HasDetails.class.isAssignableFrom(errorClass)) {
                Class<?> detailsClass = getDetailsType(errorClass);
                Schema<?> detailsSchema = ModelConverters.getInstance().read(detailsClass).get(detailsClass.getSimpleName());
                schema.getProperties().put("details", detailsSchema.description("Specific details for the error"));
                schema.getRequired().add("details");
            }

            operation.getResponses().addApiResponse(name, new ApiResponse()
                    .content(new Content().addMediaType("application/json", new MediaType().schema(schema)))
                    .description(description));
        }


    }

    private Schema<?> buildSchema(String code, String path) {

        Schema<?> schema = ModelConverters.getInstance().read(ErrorModels.ErrorResponse.class).get("ErrorResponse");

        schema.getProperties().get("title").example(HttpStatus.valueOf(Integer.parseInt(code)).getReasonPhrase());
        schema.getProperties().get("status").example(code);
        schema.getProperties().get("detail").example(getDetail(code));
        schema.getProperties().get("instance").example(RegExUtils.replaceAll(path, "\\{.*\\}", "1"));
        schema.getProperties().get("at").example("2025-10-17T12:00:00Z");

        return schema;

    }

    private boolean isErrorCode(String code) {
        try {
            int c = Integer.parseInt(code);
            return c >= 400;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String getCodeFromError(Class<?> clazz) {
        if (AuthenticationException.class.isAssignableFrom(clazz)) return "401";
        if (NoSuchElementException.class.isAssignableFrom(clazz)) return "404";
        if (IllegalStateException.class.isAssignableFrom(clazz)) return "409";
        return "500";
    }

    private String getDetail(String code) {
        return switch (code) {
            case "400" -> "Invalid input";
            case "401" -> "Missing or invalid token";
            case "403" -> "Insufficient permissions";
            case "404" -> "Resource missing";
            case "409" -> "Conflict";
            case "500" -> "Internal Server Error";
            default -> "Error";
        };
    }

    private String getDescription(String code) {
        return switch (code) {
            case "400" -> "The body of the request is malformed";
            case "401" -> "The client is not authenticated";
            case "403" -> "The client does not have permissions to access this route";
            case "404" -> "One of the requested resources was not found";
            case "409" -> "The request conflicts with the current state of the server";
            case "422" -> "The content of the body does not match the expected model";
            case "500" -> "An unexpected error happened on server side";
            default -> "Error";
        };
    }

    public Class<?> getDetailsType(Class<?> clazz) {
        return getDetailsType(clazz, 0);
    }

    public Class<?> getDetailsType(Class<?> clazz, int index) {
        for (Type type : clazz.getGenericInterfaces()) {
            if (type instanceof ParameterizedType parameterizedType) {
                Type raw = parameterizedType.getRawType();
                if (raw instanceof Class<?> rawClass && HasDetails.class.isAssignableFrom(rawClass)) {
                    Type actualType = parameterizedType.getActualTypeArguments()[index];
                    if (actualType instanceof Class<?>) {
                        return (Class<?>) actualType;
                    } else if (actualType instanceof ParameterizedType pType) {
                        return (Class<?>) pType.getRawType();
                    }
                }
            }
        }

        if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
            return getDetailsType(clazz.getSuperclass(), index);
        }

        throw new IllegalArgumentException("No generic type found for HasDetails");
    }

}
