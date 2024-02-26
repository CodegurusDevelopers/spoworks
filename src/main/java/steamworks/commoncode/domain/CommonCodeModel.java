package steamworks.commoncode.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
public class CommonCodeModel implements Cloneable{
    Long commCdId;

    String  cd;

    String cdNm;

    Integer order;

    String creatorId;

    Timestamp createdDatentime;

    String modifierId;

    Timestamp modifyDatentime;

    String activateYn;
    // 코드 영어
    String cdEn;
    // 코드 일본어
    String cdJp;
    // 코드 중국어
    String cdCn;


    List<CommonCodeModel> children;


    @Builder.Default
    Boolean hasParent = false;

    public void add(CommonCodeModel child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
    }
    @Override
    public CommonCodeModel clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
        }
        return (CommonCodeModel) obj;
    }

}
