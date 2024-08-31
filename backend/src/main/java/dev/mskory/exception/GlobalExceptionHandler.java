package dev.mskory.exception;

import jakarta.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final Map<Class<? extends Exception>, HttpStatus> exceptionStatuses =
            Map.of(EntityNotFoundException.class, HttpStatus.NOT_FOUND,
                    SQLException.class, HttpStatus.BAD_REQUEST);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleDefinedException(Exception ex, WebRequest request) throws Exception {
        Optional<Class<? extends Exception>> exception = findMatch(ex);
        if (exception.isPresent()) {
            return createResponseEntity(exceptionStatuses.get(exception.get()), ex.getLocalizedMessage());
        }
        return this.handleException(ex, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return createResponseEntity(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    }

    private Optional<Class<? extends Exception>> findMatch(Exception ex) {
        if (exceptionStatuses.containsKey(ex.getClass())) {
            return Optional.of(ex.getClass());
        }
        ArrayList<Throwable> causes = getCauses(ex, new ArrayList<>());
        return exceptionStatuses.keySet().stream()
                .filter(k -> causes.stream()
                        .anyMatch(e -> k.isAssignableFrom(e.getClass())))
                .findAny();
    }

    private ArrayList<Throwable> getCauses(Throwable ex, ArrayList<Throwable> exceptions) {
        if (ex == null) {
            return exceptions;
        }
        exceptions.add(ex);
        return getCauses(ex.getCause(), exceptions);
    }

    private ResponseEntity<Object> createResponseEntity(HttpStatus status, String detail) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return ResponseEntity.of(problemDetail).build();
    }
    //    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
//        return createResponseEntity(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
//    }
//
//    @ExceptionHandler(SQLException.class)
//    public ResponseEntity<Object> handleSQLException(SQLException ex) {

//        return createResponseEntity(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());

//    }
}
