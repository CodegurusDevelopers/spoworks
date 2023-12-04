package steamworks.codeManage.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import steamworks.codeManage.domain.model.sub.OrderType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
public class CodeChildrenModel {
    Long id;
    Integer code;
    String name;
    String displayName;
    List<String> externalCodes;
    Boolean active;
    OrderType orderType;
    List<CodeChildrenModel> children;

    public CodeChildrenModel(CodeModel codeModel) {
        this.id = codeModel.getId();
        this.code = codeModel.getCode();
        this.name = codeModel.getName();
        this.displayName = codeModel.getDisplayName();
        this.externalCodes = codeModel.getExternalCodes();
        this.active = codeModel.active;
        this.orderType = codeModel.orderType;
        this.children = new ArrayList<>();
    }
}
