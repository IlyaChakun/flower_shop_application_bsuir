package by.bsuir.dto.model.order.partial;

import by.bsuir.dto.model.order.review.OrderReviewDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class OrderPartialUpdate {

    @NotNull(message = "Order id is required.")
    private Long orderId;

    @Valid
    private OrderFloristChoiceDTO orderFloristChoice;

    @Valid
    private OrderFloristCompletionDTO orderFloristCompletion;

    @Valid
    private OrderReviewDTO orderReviewDTO;

}
