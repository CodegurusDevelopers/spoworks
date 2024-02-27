package steamworks.commoncode.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import steamworks.codeManage.domain.model.CodeModel;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CommonCodeListResponse {
    List<CommonCodeModel> codes;
}
