package by.bsuir.dto.model.cart;

import by.bsuir.dto.model.BaseAbstractDTO;
import by.bsuir.dto.model.product.AbstractFlowerProductDTO;
import by.bsuir.dto.model.product.common.FlowerLengthCostDTO;
import by.bsuir.dto.model.user.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO extends BaseAbstractDTO {

    private ClientDTO client;

    private AbstractFlowerProductDTO product;

    private FlowerLengthCostDTO flowerLengthCost;

    private Integer quantity;

}
