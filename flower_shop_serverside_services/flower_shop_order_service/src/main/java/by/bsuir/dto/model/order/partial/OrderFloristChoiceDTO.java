package by.bsuir.dto.model.order.partial;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class OrderFloristChoiceDTO {

    /**
     * chose florist
     **/
    @NotNull(message = "Florist id is required")
    private Long floristId;

}
