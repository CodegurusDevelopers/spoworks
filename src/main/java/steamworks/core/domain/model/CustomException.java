package steamworks.core.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomException extends RuntimeException{
    protected HttpStatus status;
    protected String error;
    protected String message;
}
