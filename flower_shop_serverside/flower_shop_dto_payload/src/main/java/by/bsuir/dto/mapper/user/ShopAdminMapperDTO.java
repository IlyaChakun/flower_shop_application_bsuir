package by.bsuir.dto.mapper.user;

import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.user.ShopAdminDTO;
import by.bsuir.entity.user.ShopAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShopAdminMapperDTO extends AbstractMapperDTO<ShopAdmin, ShopAdminDTO> {

    @Autowired
    public ShopAdminMapperDTO() {
        super(ShopAdmin.class, ShopAdminDTO.class);
    }
}