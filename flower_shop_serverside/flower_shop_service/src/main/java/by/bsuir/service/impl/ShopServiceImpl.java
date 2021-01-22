package by.bsuir.service.impl;

import by.bsuir.dto.mapper.company.CompanyMapperDTO;
import by.bsuir.dto.mapper.shop.ShopMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.shop.ShopDTO;
import by.bsuir.entity.common.Image;
import by.bsuir.entity.company.Company;
import by.bsuir.entity.shop.Shop;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.company.CompanyRepository;
import by.bsuir.repository.api.shop.ShopRepository;
import by.bsuir.service.api.ShopService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {

    private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

    private final CommonServiceHelper commonServiceHelper;
    private final ShopRepository shopRepository;
    private final CompanyRepository companyRepository;
    private final ShopMapperDTO shopMapper;
    private final CompanyMapperDTO companyMapper;


    @Override
    @Transactional
    public void delete(Long shopId) {
        //TODO review
        Shop shop = getShopByIdOrThrowException(shopId);
        shopRepository.delete(shop);
    }


    @Override
    public PageWrapper<ShopDTO> findAll(int page, int size) {
        Pageable pageable = commonServiceHelper.getPageable(page, size);

        Page<Shop> shops = shopRepository.findAll(pageable);

        return
                new PageWrapper<>(
                        shopMapper.toDtoList(shops.toList()),
                        shops.getTotalPages(),
                        shops.getTotalElements());
    }

    @Override
    public ShopDTO findById(Long id) {
        Shop shop = getShopByIdOrThrowException(id);
        return shopMapper.toDto(shop);
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

        Company company = companyRepository.findFirstBy();

        Shop shop = shopMapper.toEntity(shopDTO);
        shop.setCompanyId(company.getId());
        Shop savedShop = shopRepository.save(shop);

        logger.info("Saved Shop with id={}", shop.getId());

        return shopMapper.toDto(savedShop);
    }


    @Override
    @Transactional
    public ShopDTO update(ShopDTO shopDTO) {
        Shop shopFromDb = getShopByIdOrThrowException(shopDTO.getId());

        Shop shopForUpdate = shopMapper.toEntity(shopDTO);
        shopFromDb.setContacts(shopForUpdate.getContacts());
        shopFromDb.setWorkingHours(shopForUpdate.getWorkingHours());
        shopFromDb.setImage(new Image(shopDTO.getImage().getImageUrl()));

        return shopMapper.toDto(shopRepository.save(shopFromDb));
    }

    private boolean isShopExistsByAddress(String city, String address) {
        return shopRepository.findByContactsCityAndContactsAddress(city, address).isPresent();
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
