package steamworks.codeManage.domain.model.excpetion;

import org.springframework.http.HttpStatus;
import steamworks.codeManage.entity.Code;
import steamworks.core.domain.model.CustomException;

public class RecursiveParentException extends CustomException {
    public RecursiveParentException(Code code) {
        this.status = HttpStatus.BAD_REQUEST;
        this.error = "RecursiveParentException";
        this.message = String.format("%d code has a recursive child", code.getId());
    }
}
