package by.bsuir.dto.mapper.company;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.company.ShopDTO;
import by.bsuir.entity.company.bank.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShopMapperDTO extends AbstractMapperDTO<Shop, ShopDTO> {

    @Autowired
    public ShopMapperDTO() {
        super(Shop.class, ShopDTO.class);
    }
}