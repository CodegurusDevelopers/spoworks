package steamworks.commoncode.service;

import steamworks.commoncode.domain.CommonCodeMappingModel;
import steamworks.commoncode.domain.CommonCodeModel;
import steamworks.commoncode.entity.CommonCode;
import steamworks.commoncode.entity.CommonCodeMapping;

public class CommonCodeConverter {
    static public CommonCodeModel from(CommonCode code) {
        return CommonCodeModel.builder()
                .commCdId(code.getCommCdId())
                .cd(code.getCd())
                .cdNm(code.getCdNm())
                .order(code.getOrder())
                .creatorId(code.getCreatorId())
                .createdDatentime(code.getCreatedDatentime())
                .modifierId(code.getModifierId())
                .modifyDatentime(code.getModifyDatentime())
                .activateYn(code.getActivateYn())
                .cdEn(code.getCdEn())
                .cdJp(code.getCdJp())
                .cdCn(code.getCdCn())
                .build();
    }

    static public CommonCodeMappingModel fromMapping(CommonCodeMapping code) {
        return CommonCodeMappingModel.builder()
                .commCdMappingId(code.getCommCdMappingId())
                .childId(code.getChildId())
                .parentsId(code.getParentsId())
                .order(code.getOrder())
                .creatorId(code.getCreatorId())
                .createdDatentime(code.getCreatedDatentime())
                .modifierId(code.getModifierId())
                .modifyDatentime(code.getModifyDatentime())
                .build();
    }

}
