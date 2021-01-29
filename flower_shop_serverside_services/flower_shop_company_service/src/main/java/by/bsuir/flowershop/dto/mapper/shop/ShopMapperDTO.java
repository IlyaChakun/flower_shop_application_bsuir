package by.bsuir.flowershop.dto.mapper.shop;

import by.bsuir.flowershop.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.flowershop.dto.model.shop.ShopDTO;
import by.bsuir.flowershop.entity.shop.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShopMapperDTO extends AbstractMapperDTO<Shop, ShopDTO> {

    @Autowired
    public ShopMapperDTO() {
        super(Shop.class, ShopDTO.class);
    }
}
