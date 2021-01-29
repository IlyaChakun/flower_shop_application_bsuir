package by.bsuir.dto.model.cart;

import by.bsuir.dto.model.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class CartDTO extends AbstractDTO {

    private Long clientId;

    private List<CartItemDTO> cartItems = new ArrayList<>();

    private Double totalPrice = 0D;

}