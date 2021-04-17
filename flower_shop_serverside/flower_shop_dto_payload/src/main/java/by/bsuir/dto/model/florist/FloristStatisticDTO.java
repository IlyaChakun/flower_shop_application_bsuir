package by.bsuir.dto.model.florist;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FloristStatisticDTO extends BaseAbstractDTO {

    private Integer completedOrdersCount = 0;

    private Double floristRatingSum = 0D;

    private Double floristRating = 0D;

}
