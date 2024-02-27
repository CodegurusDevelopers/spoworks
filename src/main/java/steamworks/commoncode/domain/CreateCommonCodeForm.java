package steamworks.commoncode.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import steamworks.codeManage.domain.model.sub.OrderType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@ToString
public class CreateCommonCodeForm {
    @NotBlank String  cd;

    @NotBlank String cdNm;

    @NotNull Integer ord;

    @NotNull List<Long> parents;

    @NotNull String activateYn;
    // 코드 영어
    String cdEn;
    // 코드 일본어
    String cdJp;
    // 코드 중국어
    String cdCn;

    Long creatorId;
}
