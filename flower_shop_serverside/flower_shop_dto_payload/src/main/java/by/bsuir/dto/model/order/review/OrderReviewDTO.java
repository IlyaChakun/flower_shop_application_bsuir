package by.bsuir.dto.model.order.review;

import by.bsuir.dto.model.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class OrderReviewDTO extends AbstractDTO {

    private Long clientId;

    private Long orderId;

    private String text;

    private Integer rating;

}