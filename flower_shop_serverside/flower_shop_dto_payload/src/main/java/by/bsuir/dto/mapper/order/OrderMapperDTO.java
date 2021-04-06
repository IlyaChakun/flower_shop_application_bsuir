package by.bsuir.dto.mapper.order;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.order.OrderDTO;
import by.bsuir.entity.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperDTO extends AbstractMapperDTO<Order, OrderDTO> {

    @Autowired
    public OrderMapperDTO() {
        super(Order.class, OrderDTO.class);
    }
}