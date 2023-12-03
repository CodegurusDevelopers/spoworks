package steamworks.codeManage.service;

import org.springframework.stereotype.Service;
import steamworks.codeManage.domain.model.CodeModel;
import steamworks.codeManage.domain.model.form.CreateCodeForm;
import steamworks.codeManage.domain.model.form.UpdateCodeForm;

import java.util.List;

public interface CodeService {
    List<CodeModel> getAllCodes();
    CodeModel createCode(CreateCodeForm codeForm);
    CodeModel updateCode(UpdateCodeForm codeForm);
    void deleteCode(Long id);
}
