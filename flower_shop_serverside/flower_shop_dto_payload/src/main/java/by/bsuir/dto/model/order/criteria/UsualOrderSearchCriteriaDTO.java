package by.bsuir.dto.model.order.criteria;

import by.bsuir.dto.model.AbstractSearchCriteriaAndSortParamsDto;
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

}
