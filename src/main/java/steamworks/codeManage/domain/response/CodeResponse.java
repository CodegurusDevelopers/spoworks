package steamworks.codeManage.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import steamworks.codeManage.domain.model.CodeModel;

@Getter
@Setter
@AllArgsConstructor
public class CodeResponse {
    CodeModel code;
}
