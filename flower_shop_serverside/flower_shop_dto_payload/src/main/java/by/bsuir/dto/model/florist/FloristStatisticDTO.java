package by.bsuir.dto.model.florist;

import by.bsuir.dto.model.BaseAbstractDTO;
import by.bsuir.entity.BaseAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
public class FloristStatisticDTO extends BaseAbstractDTO {

    private Integer completedOrdersCount;

    private Double floristRatingSum;

    private Double floristRating;

}
