package by.bsuir.service.impl;

import by.bsuir.dto.mapper.user.ShopAdminMapperDTO;
import by.bsuir.dto.model.user.ShopAdminDTO;
import by.bsuir.entity.user.ShopAdmin;
import by.bsuir.repository.api.user.ShopAdminRepository;
import by.bsuir.service.api.ShopAdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ShopAdminServiceImpl implements ShopAdminService {

    private final ShopAdminRepository shopAdminRepository;
    private final ShopAdminMapperDTO shopAdminMapper;


    @Override
    public Optional<ShopAdminDTO> findByEmail(String email) {
        Optional<ShopAdmin> adminOptional = shopAdminRepository.findByEmail(email);
        return adminOptional.map(shopAdminMapper::toDto);
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
