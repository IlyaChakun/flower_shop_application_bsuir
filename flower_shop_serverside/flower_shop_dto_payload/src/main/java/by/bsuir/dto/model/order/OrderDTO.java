package by.bsuir.dto.model.order;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.product.AbstractFlowerProductDTO;
import by.bsuir.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderDTO extends AbstractDTO {

    private String uniqueId;//для понтов типо униклаьный ид хз зачем пригодиться

    @Size(max = 512, message = "Комментарий не может быть более 512 символов")
    private String comment;

    @Valid
    private List<AbstractFlowerProductDTO> orderProducts = new ArrayList<>();

    @Valid
    private OrderInfoDTO orderInfo;

    @Valid
    private OrderStatusDTO orderStatus;

}
