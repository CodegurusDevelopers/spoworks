package steamworks.codeManage.domain.model.form;

import lombok.Getter;
import lombok.Setter;
import steamworks.codeManage.domain.model.sub.OrderType;

import java.util.List;

@Getter
@Setter
public class UpdateCodeForm {
    Long id;
    List<String> externalCodes;
    Boolean active;
    OrderType orderType;
    List<Long> parents;
}
