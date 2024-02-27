package steamworks.commoncode.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SettingManageListResponse {
    List<SettingManageModel> settingManages;
}
