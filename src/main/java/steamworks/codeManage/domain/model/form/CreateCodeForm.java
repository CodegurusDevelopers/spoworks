package steamworks.codeManage.domain.model.form;

import lombok.Getter;
import lombok.Setter;
import steamworks.codeManage.domain.model.sub.OrderType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class CreateCodeForm {
    @NotBlank String name;
    @NotBlank String displayName;
    @Size(min = 2, max = 2) List<String> externalCodes;
    @NotNull Boolean active;
    @NotNull OrderType orderType;
    @NotNull List<Long> parents;
}
