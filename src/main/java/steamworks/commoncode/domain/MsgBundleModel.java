package steamworks.commoncode.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@SuperBuilder
@ToString
public class MsgBundleModel implements Cloneable{
    Long msgManageId;

    String msgCd;
    String msgEn;

    String msgJp;
    String msgCn;
    String msg;
    String activateYn;
    Long creatorId;
    Timestamp createdDatentime;
    Long modifierId;
    Timestamp modifyDatentime;

    Long commCdId;
    String cdNm;

}
