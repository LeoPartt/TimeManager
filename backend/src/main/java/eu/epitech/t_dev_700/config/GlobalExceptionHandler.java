package eu.epitech.t_dev_700.config;

import eu.epitech.t_dev_700.models.ErrorModel;
import eu.epitech.t_dev_700.services.exceptions.InvalidCredentials;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.DecodingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        return new ErrorModel(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Validation failed",
                request,
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(
                                FieldError::getField,
                                e -> Optional.ofNullable(e.getDefaultMessage()).orElse("")
                        ))).toResponse();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        return new ErrorModel(HttpStatus.BAD_REQUEST, "Malformed JSON body", request).toResponse();
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleConflict(IllegalStateException ex, WebRequest request) {
        return new ErrorModel(HttpStatus.CONFLICT, ex, request).toResponse();
    }

    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        return new ErrorModel(HttpStatus.NOT_FOUND, ex, request).toResponse();
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<Object> InvalidCredentialsException(InvalidCredentials ex, WebRequest request) {
        return new ErrorModel(HttpStatus.UNAUTHORIZED, ex, request).toResponse();
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Object> MalformedJwtTokenException(JwtException ex, WebRequest request) {
        return (switch (ex) {
            case ExpiredJwtException ignored ->
                    new ErrorModel(HttpStatus.UNAUTHORIZED, "Token expired", request);
            case PrematureJwtException ignored ->
                    new ErrorModel(HttpStatus.UNAUTHORIZED, "Token not active yet", request);
            case MalformedJwtException ignored ->
                    new ErrorModel(HttpStatus.BAD_REQUEST, "Invalid token format", request);
            case DecodingException ignored ->
                    new ErrorModel(HttpStatus.BAD_REQUEST, "Invalid token format", request);
            case InvalidClaimException ignored ->
                    new ErrorModel(HttpStatus.UNAUTHORIZED, "Invalid token claims", request);
            default ->
                    new ErrorModel(HttpStatus.UNAUTHORIZED, "Invalid or unauthorized token", request);
        }).toResponse();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpected(Exception ex, WebRequest request) {
        System.out.println(ex.getClass() + " " + ex.getMessage());
        return new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", request).toResponse();
    }


}
