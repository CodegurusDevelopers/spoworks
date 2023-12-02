package steamworks.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import steamworks.core.domain.response.CustomExceptionResponse;

import java.util.Objects;

@RestControllerAdvice
public class ValidExceptionController {
    /**
     * javax 를 통한 validation 실패 시 호출 되는 exception handler
     *
     * @param exception jakarta.valid 결과
     * @return { error: Check[FieldName], message: error message } 형태의 response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<CustomExceptionResponse>
        handleCustomException(MethodArgumentNotValidException exception) {
        String field = Objects.requireNonNull(exception.getBindingResult().getFieldError()).getField();
        String capitalizedField = field.substring(0, 1).toUpperCase() + field.substring(1);

        CustomExceptionResponse response = new CustomExceptionResponse(
                "Check" + capitalizedField
                , exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()
        );

        return ResponseEntity.badRequest().body(response);
    }
}
