package steamworks.commoncode.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "COMM_CD")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commCdId;

    @Column
    String  cd;

    @Column
    String cdNm;

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
