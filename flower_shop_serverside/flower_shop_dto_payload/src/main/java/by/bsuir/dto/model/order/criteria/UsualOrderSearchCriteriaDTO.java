package by.bsuir.dto.model.order.criteria;

import by.bsuir.dto.model.AbstractSearchCriteriaAndSortParamsDto;
import by.bsuir.entity.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsualOrderSearchCriteriaDTO extends AbstractSearchCriteriaAndSortParamsDto {

    private Long clientId;

    private Long floristId;

    private OrderStatus orderStatus = OrderStatus.NEW;

}
