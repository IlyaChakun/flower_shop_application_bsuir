package by.bsuir.dto.model.order.usual;

import by.bsuir.dto.model.order.BaseOrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UsualOrderDTO extends BaseOrderDTO {

    private Long clientId;

    private Long orderReviewId;

}