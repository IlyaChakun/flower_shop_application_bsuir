package by.bsuir.dto.mapper.order;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.order.buynow.BuyNowOrderDTO;
import by.bsuir.entity.order.buynow.BuyNowOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuyNowOrderMapperDTO extends AbstractMapperDTO<BuyNowOrder, BuyNowOrderDTO> {

    @Autowired
    public BuyNowOrderMapperDTO() {
        super(BuyNowOrder.class, BuyNowOrderDTO.class);
    }
}