package steamworks.commoncode.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "COMM_CD_MAPPING")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonCodeMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commCdMappingId;

    @Column
    Long  childId;

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
