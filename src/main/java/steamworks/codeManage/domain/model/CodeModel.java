package steamworks.codeManage.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import steamworks.codeManage.domain.model.sub.OrderType;
import steamworks.core.domain.model.BaseTimestampModel;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class CodeModel extends BaseTimestampModel {
    Long id;
    Integer code;
    String name;
    String displayName;
    List<String> externalCodes;
    Boolean active;
    OrderType orderType;
    List<CodeModel> parents;
}
