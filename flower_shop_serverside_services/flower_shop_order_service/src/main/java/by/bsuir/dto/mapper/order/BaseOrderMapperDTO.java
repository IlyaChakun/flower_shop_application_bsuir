package by.bsuir.dto.mapper.order;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.order.BaseOrderDTO;
import by.bsuir.entity.order.BaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseOrderMapperDTO extends AbstractMapperDTO<BaseOrder, BaseOrderDTO> {

    @Autowired
    public BaseOrderMapperDTO() {
        super(BaseOrder.class, BaseOrderDTO.class);
    }
}