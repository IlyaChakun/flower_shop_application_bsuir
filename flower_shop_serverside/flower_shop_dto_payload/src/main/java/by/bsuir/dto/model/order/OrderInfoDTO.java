package by.bsuir.dto.model.order;

import by.bsuir.dto.model.BaseAbstractDTO;
import by.bsuir.dto.model.user.ClientDTO;
import by.bsuir.dto.model.user.CourierDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderInfoDTO extends BaseAbstractDTO {

    @Valid
    private ClientDTO client;

    @Valid
    private CourierDTO courier;

    @Valid
    private OrderCostsDTO orderCosts;

    @Valid
    private OrderDeliveryAddressDTO orderAddress;

    @Valid
    private OrderPaymentTypeDTO orderPaymentType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH.mm")
    private LocalDateTime dateOfComplete;

}
