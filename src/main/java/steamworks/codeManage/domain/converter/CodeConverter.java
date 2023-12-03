package steamworks.codeManage.domain.converter;

import steamworks.codeManage.domain.model.CodeModel;
import steamworks.codeManage.entity.Code;

import java.util.stream.Collectors;

public class CodeConverter {
    static public CodeModel from(Code code) {
        return CodeModel.builder()
                .id(code.getId())
                .code(code.getCode())
                .name(code.getName())
                .displayName(code.getDisplayName())
                .externalCodes(code.getExternalCodes())
                .active(code.getActive())
                .orderType(code.getOrderType())
                .parents(code.getParents().stream().map(CodeConverter::from).collect(Collectors.toList()))
                .createdAt(code.getCreatedAt())
                .updatedAt(code.getUpdatedAt())
                .build();
    }

    static public Code to(CodeModel codeModel) {
        Code code = Code.builder()
                .id(codeModel.getId())
                .code(codeModel.getCode())
                .name(codeModel.getName())
                .displayName(codeModel.getDisplayName())
                .externalCodes(codeModel.getExternalCodes())
                .active(codeModel.getActive())
                .orderType(codeModel.getOrderType())
                .parents(codeModel.getParents().stream().map(CodeConverter::to).collect(Collectors.toList()))
                .build();
        code.setCreatedAt(codeModel.getCreatedAt());
        code.setUpdatedAt(codeModel.getUpdatedAt());

        return code;
    }
}
