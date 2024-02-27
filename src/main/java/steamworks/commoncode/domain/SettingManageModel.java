package steamworks.commoncode.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
@SuperBuilder
@ToString
public class SettingManageModel implements Cloneable{
    Long settingManageId;

    String settingCd;
    String settingValue;
    String settingMessage;
    String settingMessageEn;
    String settingMessageJp;
    String settingMessageCn;
    String activateYn;
    Long creatorId;
    Timestamp createdDatentime;
    Long modifierId;
    Timestamp modifyDatentime;

    Long commCdId;
    private String cdNm;

}
