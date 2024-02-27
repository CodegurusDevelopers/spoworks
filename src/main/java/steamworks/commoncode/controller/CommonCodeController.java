package steamworks.commoncode.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import steamworks.codeManage.domain.model.CodeModel;
import steamworks.codeManage.domain.model.form.CreateCodeForm;
import steamworks.codeManage.domain.response.CodeResponse;
import steamworks.commoncode.domain.*;
import steamworks.commoncode.entity.MsgBundle;
import steamworks.commoncode.entity.SettingManage;
import steamworks.commoncode.service.CommonCodeService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@AllArgsConstructor
public class CommonCodeController {

    private CommonCodeService commonCodeService;

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


    @GetMapping("/msgBundle:LIST")
    public ResponseEntity<MsgBundleLsitListResponse> getMsgBundles() {
        List<MsgBundleModel> codes = commonCodeService.findMsgBundles();
        return ResponseEntity.ok(new MsgBundleLsitListResponse(codes));
    }
    @PostMapping("/msgBundle:REGISTRY")
    public ResponseEntity<MsgBundleResponse> registryCode(@Valid @RequestBody CreateMsgBundleForm form) {
        MsgBundleModel code = commonCodeService.createMsgBundle(form);
        return ResponseEntity.ok(new MsgBundleResponse(code));
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
