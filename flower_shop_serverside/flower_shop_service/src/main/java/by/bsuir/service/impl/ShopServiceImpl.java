package by.bsuir.service.impl;

import by.bsuir.dto.mapper.company.CompanyMapperDTO;
import by.bsuir.dto.mapper.company.ShopMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.company.ShopDTO;
import by.bsuir.entity.company.Company;
import by.bsuir.entity.company.Contacts;
import by.bsuir.entity.company.Shop;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.CompanyRepository;
import by.bsuir.repository.api.ContactsRepository;
import by.bsuir.repository.api.ShopRepository;
import by.bsuir.service.api.CompanyService;
import by.bsuir.service.api.ShopService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {

    private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

    private final ShopRepository shopRepository;
    private final CompanyRepository companyRepository;
    private final ContactsRepository contactsRepository;
    private final CompanyService companyService;
    private final ShopMapperDTO shopMapper;
    private final CompanyMapperDTO companyMapperDTO;

    @Override
    public ShopDTO findById(Long id) {
        Shop shop = getShopByIdOrThrowException(id);
        return shopMapper.toDto(shop);
    }


    @Override
    public PageWrapper<ShopDTO> findAll(int page, int size) {
        Pageable pageable = getPageable(page, size);

        Page<Shop> shops = shopRepository.findAll(pageable);

        return
                new PageWrapper<>(
                        shopMapper.toDtoList(shops.toList()),
                        shops.getTotalPages(),
                        shops.getTotalElements());
    }

    private Pageable getPageable(int page, int size) {
        return PageRequest.of(page, size);
    }


    @Override
    @Transactional
    public ShopDTO save(ShopDTO shopDTO, Long companyId) {
        if (isShopExistsByAddress(shopDTO.getContacts().getCity(), shopDTO.getContacts().getAddress())) {
            logger.error("Shop with address={},{} exist. Just Update it!",
                    shopDTO.getContacts().getCity(),
                    shopDTO.getContacts().getAddress());
            throw new ServiceException(HttpStatus.CONFLICT.value(),
                    "shop_already_exist",
                    "Shop with address=" + shopDTO.getContacts().getCity() + ", "
                            + shopDTO.getContacts().getAddress() + " exist. Just Update it!");
        }

        Company company = companyRepository.getOne(companyId);

        Shop shop = shopMapper.toEntity(shopDTO);
        shop.setCompany(company);

        Shop savedShop = shopRepository.save(shop);

        logger.info("Saved Shop with id={}", shop.getId());

        return shopMapper.toDto(savedShop);
    }


    @Override
    @Transactional
    public ShopDTO update(ShopDTO shopDTO) {
        Shop shopFromDb = getShopByIdOrThrowException(shopDTO.getId());

        Shop shopForUpdate = shopMapper.toEntity(shopDTO);

        shopFromDb.setContacts(resolveContacts(shopForUpdate));
        shopFromDb.setWorkingHours(shopForUpdate.getWorkingHours());
        shopFromDb.setShopProducts(shopForUpdate.getShopProducts());

        return shopMapper.toDto(shopRepository.save(shopFromDb));
    }

    private Contacts resolveContacts(Shop shopForUpdate) {

        Contacts contacts;
        if (contactsRepository.findByCityAndAddress(shopForUpdate.getContacts().getCity(), shopForUpdate.getContacts().getAddress()).isPresent()) {
            contacts = contactsRepository.findByCityAndAddress(shopForUpdate.getContacts().getCity(), shopForUpdate.getContacts().getAddress()).get();
            contacts.setEmail(shopForUpdate.getContacts().getEmail());
            contacts.setFirstPhoneNumber(shopForUpdate.getContacts().getFirstPhoneNumber());
            contacts.setSecondPhoneNumber(shopForUpdate.getContacts().getSecondPhoneNumber());
        } else {
            contacts = shopForUpdate.getContacts();
        }
        return contacts;
    }


    @Override
    @Transactional
    public void delete(Long shopId, Long companyId) {
        Shop shop = getShopByIdOrThrowException(shopId);
        Company company = companyRepository.getOne(companyId);
        company.getShops().remove(shop);
        shopRepository.delete(shop);
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
