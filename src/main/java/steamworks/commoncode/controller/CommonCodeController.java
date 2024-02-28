package steamworks.commoncode.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import steamworks.commoncode.domain.*;
import steamworks.commoncode.service.MsgManageService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@AllArgsConstructor
public class CommonCodeController {

    private MsgManageService commonCodeService;

    @GetMapping("/commoncode:LIST")
    public ResponseEntity<CommonCodeListResponse> getCodes() {
        List<CommonCodeModel> codes = commonCodeService.getAllCodes();
        return ResponseEntity.ok(new CommonCodeListResponse(codes));
    }

    /**
     * TODO 권한 확인 필요.
     * @param form
     * @return
     */
    @PostMapping("/commoncode:REGISTRY")
    public ResponseEntity<CommonCodeResponse> registryCode(@Valid @RequestBody CreateCommonCodeForm form) {
        CommonCodeModel code = commonCodeService.createCommonCode(form);
        return ResponseEntity.ok(new CommonCodeResponse(code));
    }


    @GetMapping("/msgManage:LIST")
    public ResponseEntity<MsgManageLsitListResponse> getMsgManages() {
        List<MsgManageModel> codes = commonCodeService.findMsgManages();
        return ResponseEntity.ok(new MsgManageLsitListResponse(codes));
    }
    @PostMapping("/msgManage:REGISTRY")
    public ResponseEntity<MsgManageResponse> registryCode(@Valid @RequestBody CreateMsgManageForm form) {
        MsgManageModel code = commonCodeService.createMsgManage(form);
        return ResponseEntity.ok(new MsgManageResponse(code));
    }

    @GetMapping("/settingManage:LIST")
    public ResponseEntity<SettingManageListResponse> getSettingManages() {
        List<SettingManageModel> codes = commonCodeService.findSettingManages();
        return ResponseEntity.ok(new SettingManageListResponse(codes));
    }
    @PostMapping("/settingManage:REGISTRY")
    public ResponseEntity<SettingManageResponse> registryCode(@Valid @RequestBody CreateSettingManageForm form) {
        SettingManageModel code = commonCodeService.createSettingManage(form);
        return ResponseEntity.ok(new SettingManageResponse(code));
    }
}
