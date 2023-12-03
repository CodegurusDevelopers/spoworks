package steamworks.codeManage.domain.model.excpetion;

import org.springframework.http.HttpStatus;
import steamworks.core.domain.model.CustomException;

public class InvalidCodeIdException extends CustomException {
    public InvalidCodeIdException() {
        this.status = HttpStatus.BAD_REQUEST;
        this.error = "InvalidCodeIdException";
        this.message = "Check the id";
    }
}
