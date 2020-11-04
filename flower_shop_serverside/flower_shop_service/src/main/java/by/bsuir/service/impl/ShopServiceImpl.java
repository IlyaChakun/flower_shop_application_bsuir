package by.bsuir.service.impl;

import by.bsuir.dto.mapper.company.ShopMapperDTO;
import by.bsuir.dto.model.company.ShopDTO;
import by.bsuir.entity.company.Shop;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.repository.api.core.ShopRepository;
import by.bsuir.service.api.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ShopMapperDTO shopMapper;

    @Override
    public void delete(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id=" + id + " not found!"));
        shopRepository.delete(shop);
    }

    @Override
    public List<ShopDTO> findAll() {
        return shopMapper.toDtoList(shopRepository.findAll());
    }

    @Override
    public ShopDTO findById(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id=" + id + " not found!"));

        return shopMapper.toDto(shop);
    }

    @Override
    @Transactional
    public ShopDTO save(ShopDTO shopDTO) {
        return shopMapper.toDto(shopRepository.save(shopMapper.toEntity(shopDTO)));
    }


    @Override
    @Transactional
    public ShopDTO update(ShopDTO shopDTO) {
        Shop shopFromDb = shopRepository.findById(shopDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id=" + shopDTO.getId() + " not found!"));

        Shop shopForUpdate = shopMapper.toEntity(shopDTO);

        return shopMapper.toDto(shopRepository.save(shopForUpdate));
    }
}
