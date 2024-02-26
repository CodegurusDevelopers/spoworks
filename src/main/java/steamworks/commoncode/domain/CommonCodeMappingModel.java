package steamworks.commoncode.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@SuperBuilder
public class CommonCodeMappingModel {
    Long commCdMappingId;

    Long  childId;

    Long parentsId;

    Integer order;

    String creatorId;

    Timestamp createdDatentime;

    String modifierId;

    Timestamp modifyDatentime;

    List<CommonCodeModel> commonCodeModels;



}
