package steamworks.commoncode.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class CreateMsgManageForm {
    @NotBlank String  msgCd;

    @NotBlank String msg;

    @NotNull String activateYn;
    // 코드 영어
    String msgEn;
    // 코드 일본어
    String msgJp;
    // 코드 중국어
    String msgCn;
    @NotNull
    Long commCdId;
//    Long creatorId;
}
