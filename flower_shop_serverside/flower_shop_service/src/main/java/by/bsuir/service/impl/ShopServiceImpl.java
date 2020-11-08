package by.bsuir.service.impl;

import by.bsuir.dto.mapper.company.ShopMapperDTO;
import by.bsuir.dto.model.company.ShopDTO;
import by.bsuir.entity.company.Company;
import by.bsuir.entity.company.Shop;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.core.CompanyRepository;
import by.bsuir.repository.api.core.ShopRepository;
import by.bsuir.service.api.ShopService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {

    private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

    private final ShopRepository shopRepository;
    private final CompanyRepository companyRepository;
    private final ShopMapperDTO shopMapper;

    @Override
    public ShopDTO findById(Long id) {
        Shop shop = getShopByIdOrThrowException(id);
        return shopMapper.toDto(shop);
    }


    @Override
    public List<ShopDTO> findAll() {
        return shopMapper.toDtoList(shopRepository.findAll());
    }


    @Override
    @Transactional
    public ShopDTO save(ShopDTO shopDTO) {
        if (isShopExistsByAddress(shopDTO.getContacts().getCity(), shopDTO.getContacts().getAddress())) {
            logger.error("Shop with address={},{} exist. Just Update it!",
                    shopDTO.getContacts().getCity(),
                    shopDTO.getContacts().getAddress());
            throw new ServiceException(HttpStatus.CONFLICT.value(),
                    "shop_already_exist",
                    "Shop with address=" + shopDTO.getContacts().getCity() + ", "
                            + shopDTO.getContacts().getAddress() + " exist. Just Update it!");
        }

        Company company = companyRepository.getOne(shopDTO.getCompany().getId());

        Shop shop = shopMapper.toEntity(shopDTO);
        shop.setCompany(company);

        Shop savedShop = shopRepository.save(shop);

        logger.info("Saved Shop with id={}", shop.getId());

        return shopMapper.toDto(savedShop);
    }

    private boolean isShopExistsByAddress(String city, String address) {
        return shopRepository.findByContactsCityAndContactsAddress(city, address).isPresent();
    }


    @Override
    @Transactional
    public ShopDTO update(ShopDTO shopDTO) {
        Shop shopFromDb =  getShopByIdOrThrowException(shopDTO.getId());

        Shop shopForUpdate = shopMapper.toEntity(shopDTO);

        shopFromDb.setCompany(shopForUpdate.getCompany());
        shopFromDb.setContacts(shopForUpdate.getContacts());
        shopFromDb.setShopProducts(shopForUpdate.getShopProducts());
        shopFromDb.setWorkingHours(shopForUpdate.getWorkingHours());

        return shopMapper.toDto(shopRepository.save(shopForUpdate));
    }


    @Override
    public void delete(Long id) {
        Shop shop = getShopByIdOrThrowException(id);
        shopRepository.delete(shop);
    }


    private Shop getShopByIdOrThrowException(Long id) {
        return shopRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("Shop with id=" + id + " not found!");
                            return new ResourceNotFoundException("Shop with id=" + id + " not found!");
                        }
                );
    }

}
