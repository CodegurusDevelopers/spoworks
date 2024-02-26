package steamworks.commoncode.entity;

import lombok.*;
import steamworks.core.entity.BaseTimestampEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commCdId;

    @Column
    String  cd;

    @Column
    String cdNm;

    @Column
    Integer order;

    @Column
    String creatorId;

    @Column
    Timestamp createdDatentime;

    @Column
    String modifierId;

    @Column
    Timestamp modifyDatentime;

    @Column
    String activateYn;
    @Column
    // 코드 영어
    String cdEn;
    @Column
    // 코드 일본어
    String cdJp;
    @Column
    // 코드 중국어
    String cdCn;



}
