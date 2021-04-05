package by.bsuir.dto.mapper.order;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.order.review.OrderReviewDTO;
import by.bsuir.entity.order.review.OrderReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderReviewMapperDTO extends AbstractMapperDTO<OrderReview, OrderReviewDTO> {

    @Autowired
    public OrderReviewMapperDTO() {
        super(OrderReview.class, OrderReviewDTO.class);
    }
}