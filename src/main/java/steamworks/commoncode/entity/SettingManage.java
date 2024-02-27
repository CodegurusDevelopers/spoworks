package steamworks.commoncode.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "SETTING_MANAGE")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SettingManage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long settingManageId;

    @Column
    String settingCd;
    @Column
    String settingValue;
    @Column
    String settingMessage;
    @Column
    String settingMessageEn;
    @Column
    String settingMessageJp;
    @Column
    String settingMessageCn;
    @Column
    String activateYn;
    @Column
    Long commCdId;
    @Column
    Long creatorId;

    @Column
    Timestamp createdDatentime;

    @Column
    Long modifierId;

    @Column
    Timestamp modifyDatentime;

}
