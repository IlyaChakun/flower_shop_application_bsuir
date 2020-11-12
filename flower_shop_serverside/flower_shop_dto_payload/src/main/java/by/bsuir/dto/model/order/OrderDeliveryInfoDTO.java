package by.bsuir.dto.model.order;

import by.bsuir.dto.model.BaseAbstractDTO;
import by.bsuir.entity.order.OrderDeliveryAddress;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class OrderDeliveryInfoDTO extends BaseAbstractDTO {

    private OrderDeliveryAddress orderDeliveryAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH.mm")
    private LocalTime beginDeliveryTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH.mm")
    private LocalTime endDeliveryTime;

}
