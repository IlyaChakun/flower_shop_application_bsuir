package by.bsuir.dto.mapper.cart;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.cart.CartDTO;
import by.bsuir.entity.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CartMapperDTO extends AbstractMapperDTO<Cart, CartDTO> {

    @Autowired
    public CartMapperDTO() {
        super(Cart.class, CartDTO.class);
    }
}