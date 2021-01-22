package by.bsuir.dto.model.product;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductLengthCostDTO extends BaseAbstractDTO {

    private Double stemLength;

    private Double price;

}
