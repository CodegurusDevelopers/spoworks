package steamworks.codeManage.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import steamworks.codeManage.domain.model.CodeChildrenModel;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CodeChildrenResponse {
    List<CodeChildrenModel> code;
}
