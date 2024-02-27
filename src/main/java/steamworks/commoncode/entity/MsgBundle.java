package steamworks.commoncode.entity;

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
public class MsgBundle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long msgManageId;

    @Column
    String msgCd;

    @Column
    Long parentsId;

    @Column
    Integer ord;

    @Column
    Long creatorId;

    @Column
    Timestamp createdDatentime;

    @Column
    Long modifierId;

    @Column
    Timestamp modifyDatentime;

}
