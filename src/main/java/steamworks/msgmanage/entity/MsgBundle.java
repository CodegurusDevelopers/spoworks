package steamworks.msgmanage.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "MSG_MANAGE")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MsgManage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long msgManageId;

    @Column
    String msgCd;
    @Column
    String msgEn;

    @Column
    String msgJp;
    @Column
    String msgCn;
    @Column
    String msg;
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
