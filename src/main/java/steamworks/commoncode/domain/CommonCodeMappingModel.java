package steamworks.commoncode.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
public class CommonCodeMappingModel {
    Long commCdMappingId;

    Long  childId;

    Long parentsId;

    Integer ord;

    Long creatorId;

    Timestamp createdDatentime;

    Long modifierId;

    Timestamp modifyDatentime;

    List<CommonCodeModel> commonCodeModels;



}
