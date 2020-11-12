package by.bsuir.dto.mapper.basket;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.basket.BasketDTO;
import by.bsuir.entity.basket.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasketMapperDTO extends AbstractMapperDTO<Basket, BasketDTO> {

    @Autowired
    public BasketMapperDTO() {
        super(Basket.class, BasketDTO.class);
    }
}