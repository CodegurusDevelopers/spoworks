package steamworks.codeManage.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import steamworks.codeManage.domain.model.CodeChildrenModel;
import steamworks.codeManage.domain.model.CodeModel;
import steamworks.codeManage.domain.model.form.CreateCodeForm;
import steamworks.codeManage.domain.model.form.UpdateCodeForm;
import steamworks.codeManage.domain.request.DeleteCodeRequest;
import steamworks.codeManage.domain.response.CodeChildrenResponse;
import steamworks.codeManage.domain.response.CodeListResponse;
import steamworks.codeManage.domain.response.CodeResponse;
import steamworks.codeManage.service.CodeService;
import steamworks.core.domain.response.SimpleStatusResponse;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@AllArgsConstructor
public class CodeManageController {
    final CodeService codeService;

    @PostMapping("/code:LIST")
    public ResponseEntity<CodeListResponse> getCodes() {
        List<CodeModel> codes = codeService.getAllCodes();
        return ResponseEntity.ok(new CodeListResponse(codes));
    }

    @PostMapping("/code:LIST_CHILDREN")
    public ResponseEntity<CodeChildrenResponse> getCodeChildrenHierarchy() {
        List<CodeChildrenModel> codes = codeService.getChildrenHierarchyCodes();
        return ResponseEntity.ok(new CodeChildrenResponse(codes));
    }

    @PostMapping("/code:UPDATE")
    public ResponseEntity<CodeResponse> updateCode(@Valid @RequestBody UpdateCodeForm form) {
        CodeModel code = codeService.updateCode(form);
        return ResponseEntity.ok(new CodeResponse(code));
    }

    @PostMapping("/code:REGISTRY")
    public ResponseEntity<CodeResponse> registryCode(@Valid @RequestBody CreateCodeForm form) {
        CodeModel code = codeService.createCode(form);
        return ResponseEntity.ok(new CodeResponse(code));
    }

    @PostMapping("/code:DELETE")
    public ResponseEntity<SimpleStatusResponse> deleteCode(@Valid @RequestBody DeleteCodeRequest request) {
        codeService.deleteCode(request.getId());
        return ResponseEntity.ok(new SimpleStatusResponse(true));
    }
}
