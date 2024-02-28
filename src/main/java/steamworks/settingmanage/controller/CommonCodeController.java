package steamworks.settingmanage.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import steamworks.settingmanage.domain.*;
import steamworks.settingmanage.service.MsgManageService;
import steamworks.settingmanage.service.SettingManageService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@AllArgsConstructor
public class SettingManageController {

    private SettingManageService settingManageService;


    @GetMapping("/settingManage:LIST")
    public ResponseEntity<SettingManageListResponse> getSettingManages() {
        List<SettingManageModel> codes = settingManageService.findSettingManages();
        return ResponseEntity.ok(new SettingManageListResponse(codes));
    }
    @PostMapping("/settingManage:REGISTRY")
    public ResponseEntity<SettingManageResponse> registryCode(@Valid @RequestBody CreateSettingManageForm form) {
        SettingManageModel code = settingManageService.createSettingManage(form);
        return ResponseEntity.ok(new SettingManageResponse(code));
    }
}
