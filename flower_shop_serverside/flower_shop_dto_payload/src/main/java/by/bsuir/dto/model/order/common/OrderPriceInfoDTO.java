package by.bsuir.dto.model.order.common;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderPriceInfoDTO {

    @NotNull(message = "totalAmount is required")
    private Double totalAmount;

    private Double clientPersonalDiscount; // at the order moment
}
