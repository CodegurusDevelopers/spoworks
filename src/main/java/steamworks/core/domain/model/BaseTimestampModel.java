package steamworks.core.domain.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Getter
@Setter
@SuperBuilder
public abstract class BaseTimestampModel {
    protected ZonedDateTime createdAt;
    protected ZonedDateTime updatedAt;
}
