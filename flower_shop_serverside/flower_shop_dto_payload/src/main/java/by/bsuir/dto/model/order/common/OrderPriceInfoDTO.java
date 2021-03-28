package by.bsuir.dto.model.order.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class OrderPriceInfoDTO {

    @NotNull(message = "totalAmount is required")
    private Double totalAmount;

    // private Double clientPersonalDiscount;// at the order moment


}
