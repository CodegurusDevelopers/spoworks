package steamworks.settingmanage.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CreateSettingManageForm {
    @NotBlank public String settingCd;
    @NotBlank  public String settingValue;
    @NotBlank  public String settingMessage;
    public String settingMessageEn;
    public String settingMessageJp;
    public String settingMessageCn;
    @NotBlank  public String activateYn;
    @NotNull public Long commCdId;
}
