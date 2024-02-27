package steamworks.commoncode.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import steamworks.codeManage.domain.model.CodeModel;

@Getter
@Setter
@AllArgsConstructor
public class CommonCodeResponse {
    CommonCodeModel code;
}
