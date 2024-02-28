package steamworks.msgmanage.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MsgManageLsitListResponse {
    List<MsgManageModel> MsgManageModels;
}
