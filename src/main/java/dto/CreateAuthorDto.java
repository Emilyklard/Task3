package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateAuthorDto {
    String first_name;
    String last_name;
}
