package by.bsuir.service;

import by.bsuir.repository.api.company.CompanyRepository;
import by.bsuir.repository.api.shop.ShopRepository;
import by.bsuir.dto.mapper.shop.ShopMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.company.ContactsDTO;
import by.bsuir.dto.model.shop.ShopDTO;
import by.bsuir.entity.common.Image;
import by.bsuir.entity.company.Company;
import by.bsuir.entity.shop.Shop;
import by.bsuir.payload.ResourceNotFoundException;
import by.bsuir.payload.ServiceException;
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

    private static final Logger log = LoggerFactory.getLogger(ShopServiceImpl.class);

    private final CommonServiceHelper commonServiceHelper;

    private final ShopRepository shopRepository;
    private final CompanyRepository companyRepository;

    private final ShopMapperDTO shopMapper;


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
        commonServiceHelper.checkIdOrThrowException(id, "shop_id_invalid_exception_error", "Shop id is invalid!");

        Shop shop = getShopByIdOrThrowException(id);
        return shopMapper.toDto(shop);
    }


    @Override
    @Transactional
    public ShopDTO save(ShopDTO shopDTO) {
        checkIfShopExistsByAddressOrThrowException(shopDTO.getContacts());

        Company company = companyRepository.findFirstBy(); //TODO

        Shop shop = shopMapper.toEntity(shopDTO);
        shop.setCompanyId(company.getId());
        Shop savedShop = shopRepository.save(shop);

        log.info("Saved Shop with id={}", shop.getId());

        return shopMapper.toDto(savedShop);
    }


    @Override
    @Transactional
    public ShopDTO update(ShopDTO shopDTO) {

        commonServiceHelper.checkIdOrThrowException(shopDTO.getId(), "shop_id_absent_exception_error", "Shop id is required for update!");

        Shop shopFromDb = getShopByIdOrThrowException(shopDTO.getId());

        Shop shopForUpdate = shopMapper.toEntity(shopDTO);
        shopFromDb.setContacts(shopForUpdate.getContacts());
        shopFromDb.setWorkingHours(shopForUpdate.getWorkingHours());
        shopFromDb.setImage(new Image(shopDTO.getImage().getImageUrl()));

        return shopMapper.toDto(shopRepository.save(shopFromDb));
    }

    private void checkIfShopExistsByAddressOrThrowException(ContactsDTO contacts) {

        commonServiceHelper.checkIdOrThrowException(contacts.getCityId(), "city_id_absent_exception_error", "City id can`t be null!");

        if (shopRepository.findByContactsCityIdAndContactsAddress(contacts.getCityId(), contacts.getAddress()).isPresent()) {

            log.error("Shop with cityId={} address={} exist. Just Update it!",
                    contacts.getCityId(),
                    contacts.getAddress());
            throw new ServiceException(HttpStatus.CONFLICT.value(),
                    "shop_already_exist",
                    "Shop with cityId=" + contacts.getCityId() + ", address="
                            + contacts.getAddress() + " exist. Just Update it!");
        }
    }


    private Shop getShopByIdOrThrowException(Long id) {
        return shopRepository.findById(id)
                .orElseThrow(() -> {
                            log.error("Shop with id=" + id + " not found!");
                            return new ResourceNotFoundException("Shop with id=" + id + " not found!");
                        }
                );
    }
}
