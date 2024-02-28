package steamworks.msgmanage.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import steamworks.msgmanage.domain.*;
import steamworks.msgmanage.service.MsgManageService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@AllArgsConstructor
public class CommonCodeController {

    private MsgManageService msgManageService;

    @GetMapping("/msgmanage:LIST")
    public ResponseEntity<CommonCodeListResponse> getCodes() {
        List<CommonCodeModel> codes = msgManageService.getAllCodes();
        return ResponseEntity.ok(new CommonCodeListResponse(codes));
    }

    /**
     * TODO 권한 확인 필요.
     * @param form
     * @return
     */
    @PostMapping("/msgmanage:REGISTRY")
    public ResponseEntity<CommonCodeResponse> registryCode(@Valid @RequestBody CreateCommonCodeForm form) {
        CommonCodeModel code = msgManageService.createCommonCode(form);
        return ResponseEntity.ok(new CommonCodeResponse(code));
    }


    @GetMapping("/msgManage:LIST")
    public ResponseEntity<MsgManageLsitListResponse> getMsgManages() {
        List<MsgManageModel> codes = msgManageService.findMsgManages();
        return ResponseEntity.ok(new MsgManageLsitListResponse(codes));
    }
    @PostMapping("/msgManage:REGISTRY")
    public ResponseEntity<MsgManageResponse> registryCode(@Valid @RequestBody CreateMsgManageForm form) {
        MsgManageModel code = msgManageService.createMsgManage(form);
        return ResponseEntity.ok(new MsgManageResponse(code));
    }

}
