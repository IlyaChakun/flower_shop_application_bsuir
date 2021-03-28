package by.bsuir.dto.mapper.order;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.order.delivery.DeliveryTypeDTO;
import by.bsuir.entity.order.delivery.DeliveryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryTypeMapperDTO extends AbstractMapperDTO<DeliveryType, DeliveryTypeDTO> {

    @Autowired
    public DeliveryTypeMapperDTO() {
        super(DeliveryType.class, DeliveryTypeDTO.class);
    }
}