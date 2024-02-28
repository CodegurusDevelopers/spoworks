package steamworks.settingmanage.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import steamworks.settingmanage.domain.*;
import steamworks.settingmanage.entity.CommonCode;
import steamworks.settingmanage.entity.CommonCodeMapping;
import steamworks.settingmanage.entity.MsgManage;
import steamworks.settingmanage.repository.CommonCodeMappingRepository;
import steamworks.settingmanage.repository.CommonCodeRepository;
import steamworks.settingmanage.repository.MsgManageRepository;
import steamworks.settingmanage.repository.SettingManageRepository;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
@Slf4j
@Service
@AllArgsConstructor
public class SettingManageService {

    private CommonCodeRepository commonCodeRepository;
    private CommonCodeMappingRepository commonCodeMappingRepository;
    private MsgManageRepository msgManageRepository;
    private SettingManageRepository settingManageRepository;

    public List<SettingManageModel> findSettingManages() {
        List<SettingManageModel> settingManageModels = getSettingManages();
        for (SettingManageModel model : settingManageModels) {
            CommonCode code = commonCodeRepository.findByCommCdId(model.getCommCdId());
            model.setCdNm(code.getCdNm());
        }

        return settingManageModels;
    }
    private List<SettingManageModel> getSettingManages() {
        return settingManageRepository.findAll().stream()
                .map(CommonCodeConverter::fromSettingManage)
                .collect(Collectors.toList());
    }
    public SettingManageModel createSettingManage(CreateSettingManageForm it) {
        steamworks.settingmanage.entity.SettingManage settingManage = steamworks.settingmanage.entity.SettingManage.builder()
                .settingCd(it.settingCd)
                .settingValue(it.settingValue)
                .settingMessage(it.settingMessage)
                .settingMessageEn(it.settingMessageEn)
                .settingMessageJp(it.settingMessageJp)
                .settingMessageCn(it.settingMessageCn)
                .activateYn(it.activateYn)
                .creatorId(99999l)  // FIXME
                .createdDatentime(now())
                .commCdId(it.commCdId)
                .build();

        steamworks.settingmanage.entity.SettingManage settingManage1 = settingManageRepository.save(settingManage);
        return CommonCodeConverter.fromSettingManage(settingManage1);
    }
}
