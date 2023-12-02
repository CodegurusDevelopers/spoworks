package steamworks.core.domain.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SimpleRequest {
    @NotNull
    Boolean status;
}
