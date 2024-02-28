package steamworks.commoncode.service;

import steamworks.commoncode.domain.CommonCodeMappingModel;
import steamworks.commoncode.domain.CommonCodeModel;
import steamworks.commoncode.entity.CommonCode;
import steamworks.commoncode.entity.CommonCodeMapping;

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

}
