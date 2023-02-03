package hu.acsaifz.blogapp.exception;

import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException exception){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problem.setTitle(String.format("%s not found", exception.getResourceName()));
        problem.setType(URI.create(String.format("/%s/not-found", exception.getResourceName().toLowerCase())));
        problem.setProperty("timestamp", LocalDateTime.now());
        return problem;
    }

    @ExceptionHandler(BlogApiException.class)
    public ProblemDetail handleBlogApiException(BlogApiException exception){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(exception.getStatus(), exception.getMessage());
        problem.setTitle("Bad request");
        problem.setType(URI.create("/bad-request"));
        problem.setProperty("timestamp", LocalDateTime.now());
        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGlobalException(Exception exception){
        exception.printStackTrace();
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        problem.setTitle("Internal server error");
        problem.setProperty("timestamp", LocalDateTime.now());
        return problem;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();

        ex.getAllErrors().forEach(
                error -> {
                    String fieldName = ((FieldError)error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName,message);
                }
        );
        ProblemDetail problem = ex.getBody();

        problem.setProperty("invalid-params", errors);
        problem.setProperty("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(problem, status);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException exception){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.getMessage());

        problem.setTitle("Forbidden");
        problem.setType(URI.create("/access-denied"));
        problem.setProperty("timestamp", LocalDateTime.now());
        return problem;
    }

    @ExceptionHandler(JwtValidationException.class)
    public ProblemDetail handleJwtValidationException(JwtValidationException exception){
        List<OAuth2Error> errors = new ArrayList<>(exception.getErrors());
        OAuth2Error error = errors.get(0);
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, error.getDescription());
        problem.setTitle(error.getErrorCode());
        problem.setType(URI.create("/unauthorized"));
        problem.setProperty("timestamp", LocalDateTime.now());
        return problem;
    }

    @ExceptionHandler(BadJwtException.class)
    public ProblemDetail handleBadJwtException(BadJwtException exception){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        problem.setTitle(OAuth2ErrorCodes.INVALID_REQUEST);
        problem.setType(URI.create("/" + OAuth2ErrorCodes.INVALID_REQUEST));
        problem.setProperty("timestamp", LocalDateTime.now());
        return problem;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException(BadCredentialsException exception){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Wrong username or password");
        problem.setTitle(exception.getMessage());
        problem.setType(URI.create("/bad-credentials"));
        problem.setProperty("timestamp", LocalDateTime.now());
        return problem;
    }
}
