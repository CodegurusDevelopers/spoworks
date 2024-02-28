package steamworks.msgmanage.service;

import steamworks.msgmanage.domain.MsgManageModel;
import steamworks.msgmanage.entity.MsgManage;

public class MsgManageConverter {

    static public MsgManageModel fromMsg(MsgManage it) {
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

}
