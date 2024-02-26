package steamworks.commoncode.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonCodeMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commCdMappingId;

    @Column
    Long  childId;

    @Column
    Long parentsId;

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

}
