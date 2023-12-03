package steamworks.codeManage.entity;

import lombok.*;
import steamworks.codeManage.domain.model.sub.OrderType;
import steamworks.core.entity.BaseTimestampEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Code extends BaseTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    Integer code;

    @Column
    String name;

    @Column
    String displayName;

    @ElementCollection
    List<String> externalCodes;

    @Column
    Boolean active;

    @Column
    OrderType orderType;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    List<Code> parents;
}
