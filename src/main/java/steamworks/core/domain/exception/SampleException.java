package steamworks.core.domain.exception;

import org.springframework.http.HttpStatus;
import steamworks.core.domain.model.CustomException;

public class SampleException extends CustomException {
    public SampleException() {
        this.status = HttpStatus.BAD_REQUEST;
        this.error = "SampleError";
        this.message = "OCCUR ERROR";
    }
}
