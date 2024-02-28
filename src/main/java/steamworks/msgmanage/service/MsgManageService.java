package steamworks.msgmanage.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import steamworks.msgmanage.domain.*;
import steamworks.msgmanage.entity.MsgManage;
import steamworks.msgmanage.repository.MsgManageRepository;

import java.util.*;
import java.util.stream.Collectors;
@Slf4j
@Service
@AllArgsConstructor
public class MsgManageService {

    private MsgManageRepository msgManageRepository;

     public List<MsgManageModel> findMsgManage() {
        List<MsgManageModel> msgManageModels = getMsgManages();
        for (MsgManageModel model : msgManageModels) {
            CommonCode code = commonCodeRepository.findByCommCdId(model.getCommCdId());
            model.setCdNm(code.getCdNm());
        }

        return msgManageModels;
    }

    private List<MsgManageModel> getMsgManages() {
        return msgManageRepository.findAll().stream()
                .map(CommonCodeConverter::fromMsgBungle)
                .collect(Collectors.toList());
    }

    public MsgManageModel createMsgManage(CreateMsgManageForm form) {

        MsgManage msgManage = MsgManage.builder()
                .msgCd(form.getMsgCd())
                .msg(form.getMsg())
                .msgEn(form.getMsgEn())
                .msgJp(form.getMsgJp())
                .msgCn(form.getMsgCn())
                .activateYn(form.getActivateYn())
                .creatorId(99999l)  // FIXME
                .createdDatentime(now())
                .commCdId(form.getCommCdId())
                .build();

        MsgManage bundle = msgManageRepository.save(msgManage);
        return CommonCodeConverter.fromMsgBungle(bundle);
    }

   
}
