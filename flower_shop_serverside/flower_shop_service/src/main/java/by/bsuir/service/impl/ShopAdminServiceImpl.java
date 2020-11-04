package by.bsuir.service.impl;

import by.bsuir.dto.mapper.user.ShopAdminMapperDTO;
import by.bsuir.dto.model.user.ShopAdminDTO;
import by.bsuir.entity.user.ShopAdmin;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.repository.api.user.ShopAdminRepository;
import by.bsuir.service.api.ShopAdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShopAdminServiceImpl implements ShopAdminService {

    private final ShopAdminRepository shopAdminRepository;
    private final ShopAdminMapperDTO shopAdminMapper;


    @Override
    public ShopAdminDTO findByEmail(String email) {
        ShopAdmin admin = shopAdminRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Admin with such a email " + email + " is absent in our base"));

        return shopAdminMapper.toDto(admin);
    }


    @Override
    public ShopAdminDTO getByEmail(String email) {
        return shopAdminMapper.toDto(shopAdminRepository.getByEmail(email));
    }

    @Override
    public Boolean existsByEmail(String email) {
        return shopAdminRepository.existsByEmail(email);
    }
}
