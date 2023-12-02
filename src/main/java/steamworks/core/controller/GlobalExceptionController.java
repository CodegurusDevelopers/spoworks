package steamworks.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import steamworks.core.domain.model.CustomException;
import steamworks.core.domain.response.CustomExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionResponse> handleException(CustomException exception) {
        return ResponseEntity.status(exception.getStatus()).body(CustomExceptionResponse.builder()
                        .message(exception.getMessage())
                        .error(exception.getError())
                        .build());
    }
}
