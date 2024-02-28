package steamworks.settingmanage.service;

import steamworks.settingmanage.domain.SettingManageModel;
import steamworks.settingmanage.entity.SettingManage;

public class SettingManageConverter {

    static public SettingManageModel fromSettingManage(SettingManage it) {
        return SettingManageModel.builder()
                .settingManageId(it.getSettingManageId())
                .settingCd(it.getSettingCd())
                .settingValue(it.getSettingValue())
                .settingMessage(it.getSettingMessage())
                .settingMessageEn(it.getSettingMessageEn())
                .settingMessageJp(it.getSettingMessageJp())
                .settingMessageCn(it.getSettingMessageCn())
                .creatorId(it.getCreatorId())
                .createdDatentime(it.getCreatedDatentime())
                .modifierId(it.getModifierId())
                .modifyDatentime(it.getModifyDatentime())
                .commCdId(it.getCommCdId())
                .activateYn(it.getActivateYn())
                .build();
    }
}
