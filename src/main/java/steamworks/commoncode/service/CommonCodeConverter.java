package steamworks.commoncode.service;

import steamworks.commoncode.domain.CommonCodeMappingModel;
import steamworks.commoncode.domain.CommonCodeModel;
import steamworks.commoncode.domain.MsgManageModel;
import steamworks.commoncode.domain.SettingManageModel;
import steamworks.commoncode.entity.CommonCode;
import steamworks.commoncode.entity.CommonCodeMapping;
import steamworks.commoncode.entity.MsgManage;
import steamworks.commoncode.entity.SettingManage;

public class CommonCodeConverter {
    static public CommonCodeModel from(CommonCode it) {
        return CommonCodeModel.builder()
                .commCdId(it.getCommCdId())
                .cd(it.getCd())
                .cdNm(it.getCdNm())
                .ord(it.getOrd())
                .creatorId(it.getCreatorId())
                .createdDatentime(it.getCreatedDatentime())
                .modifierId(it.getModifierId())
                .modifyDatentime(it.getModifyDatentime())
                .activateYn(it.getActivateYn())
                .cdEn(it.getCdEn())
                .cdJp(it.getCdJp())
                .cdCn(it.getCdCn())
                .build();
    }

    static public CommonCodeMappingModel fromMapping(CommonCodeMapping it) {
        return CommonCodeMappingModel.builder()
                .commCdMappingId(it.getCommCdMappingId())
                .childId(it.getChildId())
                .parentsId(it.getParentsId())
                .ord(it.getOrd())
                .creatorId(it.getCreatorId())
                .createdDatentime(it.getCreatedDatentime())
                .modifierId(it.getModifierId())
                .modifyDatentime(it.getModifyDatentime())
                .build();
    }
    static public MsgManageModel fromMsgBungle(MsgManage it) {
        return MsgManageModel.builder()
                .msgManageId(it.getMsgManageId())
                .msgCd(it.getMsgCd())
                .msg(it.getMsg())
                .msgEn(it.getMsgEn())
                .msgJp(it.getMsgJp())
                .msgCn(it.getMsgCn())
                .creatorId(it.getCreatorId())
                .createdDatentime(it.getCreatedDatentime())
                .modifierId(it.getModifierId())
                .modifyDatentime(it.getModifyDatentime())
                .commCdId(it.getCommCdId())
                .activateYn(it.getActivateYn())
                .build();
    }

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
