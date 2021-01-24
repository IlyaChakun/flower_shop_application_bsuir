package by.bsuir.dto.model.order.common;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class OrderProductDTO extends BaseAbstractDTO {


    private Long productId;

//    @Column(name = "product_length_cost_id")
//    private Long productLengthCostId;


    private Integer quantity;

}