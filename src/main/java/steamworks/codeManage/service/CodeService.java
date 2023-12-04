package steamworks.codeManage.service;

import steamworks.codeManage.domain.model.CodeChildrenModel;
import steamworks.codeManage.domain.model.CodeModel;
import steamworks.codeManage.domain.model.form.CreateCodeForm;
import steamworks.codeManage.domain.model.form.UpdateCodeForm;

import java.util.List;

public interface CodeService {
    List<CodeModel> getAllCodes();
    List<CodeChildrenModel> getChildrenHierarchyCodes();
    CodeModel createCode(CreateCodeForm codeForm);
    CodeModel updateCode(UpdateCodeForm codeForm);
    void deleteCode(Long id);
}
