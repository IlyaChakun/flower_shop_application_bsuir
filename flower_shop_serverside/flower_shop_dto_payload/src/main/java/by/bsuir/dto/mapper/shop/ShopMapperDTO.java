package by.bsuir.dto.mapper.shop;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.company.ShopDTO;
import by.bsuir.entity.company.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShopMapperDTO extends AbstractMapperDTO<Shop, ShopDTO> {

    @Autowired
    public ShopMapperDTO() {
        super(Shop.class, ShopDTO.class);
    }
}
