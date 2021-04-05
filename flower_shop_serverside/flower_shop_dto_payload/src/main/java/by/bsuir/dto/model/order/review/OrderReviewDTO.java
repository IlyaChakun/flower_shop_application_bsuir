package by.bsuir.dto.model.order.review;

import by.bsuir.dto.model.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class OrderReviewDTO extends AbstractDTO {

    @NotNull(message = "client id required")
    private Long clientId;

    @NotNull(message = "text required")
    private String text;

    @NotNull(message = "rating required")
    private Integer rating;

}