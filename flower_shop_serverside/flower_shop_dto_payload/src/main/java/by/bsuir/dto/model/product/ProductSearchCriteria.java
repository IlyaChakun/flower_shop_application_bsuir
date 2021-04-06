package by.bsuir.dto.model.product;

import by.bsuir.dto.model.AbstractSearchCriteriaAndSortParamsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductSearchCriteria extends AbstractSearchCriteriaAndSortParamsDto {

    private Long categoryId;

    private Double startPrice;

    private Double endPrice;

    private Boolean isDeleted = false;//можно ментяь напрмиер для админа
}
