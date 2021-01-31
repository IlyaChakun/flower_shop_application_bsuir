package by.bsuir.dto.mapper.order;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.order.usual.UsualOrderDTO;
import by.bsuir.entity.order.usual.UsualOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsualOrderMapperDTO extends AbstractMapperDTO<UsualOrder, UsualOrderDTO> {

    @Autowired
    public UsualOrderMapperDTO() {
        super(UsualOrder.class, UsualOrderDTO.class);
    }
}