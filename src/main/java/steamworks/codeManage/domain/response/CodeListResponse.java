package steamworks.codeManage.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import steamworks.codeManage.domain.model.CodeModel;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CodeListResponse {
    List<CodeModel> codes;
}
