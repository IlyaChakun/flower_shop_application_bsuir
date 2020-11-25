package by.bsuir.service.impl;

import by.bsuir.dto.mapper.product.FlowerMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.SearchAndSortParamDto;
import by.bsuir.dto.model.product.flower.FlowerDTO;
import by.bsuir.entity.common.Image;
import by.bsuir.entity.company.Shop;
import by.bsuir.entity.product.flower.Flower;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.FlowerRepository;
import by.bsuir.service.api.FlowerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FlowerServiceImpl implements FlowerService {

    private static final Logger logger = LoggerFactory.getLogger(FlowerServiceImpl.class);

    private final FlowerRepository flowerRepository;
    private final FlowerMapperDTO flowerMapper;

    private final ProductCommonServiceHelper productCommonServiceHelper;

    @Override
    public FlowerDTO findById(Long id) {
        Flower flower = flowerRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("Flower with id={} not found!", id);
                            return new ResourceNotFoundException("Flower with id=" + id + " not found!");
                        }
                );

        return flowerMapper.toDto(flower);
    }

    @Override
    @Transactional
    public FlowerDTO save(FlowerDTO flowerDTO) {

        if (Objects.isNull(flowerDTO.getShop()) || Objects.isNull(flowerDTO.getShop().getId())) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                    "absent_shop_error",
                    "Shop id must be specified!");
        }

        Shop shop = productCommonServiceHelper.resolveShop(flowerDTO);

        Flower flower = flowerMapper.toEntity(flowerDTO);
        flower.setFlowerColors(productCommonServiceHelper.resolveFlowerColors(flowerDTO.getFlowerColors()));
        flower.setFlowerSorts(productCommonServiceHelper.resolveFlowerSorts(flowerDTO.getFlowerSorts()));
        flower.setCountry(productCommonServiceHelper.resolveCountry(flowerDTO.getCountry()));
        //
        flower.setShop(shop);
        shop.getShopProducts().add(flower);
        //
        Flower savedFlower = flowerRepository.save(flower);

        logger.info("Saved Flower with id={} and type={}", flower.getId(), flower.getFlowerType());

        return flowerMapper.toDto(savedFlower);
    }


    @Override
    @Transactional
    public FlowerDTO update(FlowerDTO flowerDTO) {
        if (!isFlowerExistById(flowerDTO.getId())) {
            logger.error("Flower with id={} doesn't exist!", flowerDTO.getId());
            throw new ServiceException(HttpStatus.NOT_FOUND.value(),
                    "flower_not_found",
                    "Flower with id=" + flowerDTO.getId() + " doesn't exist!");
        }

        Shop shop = productCommonServiceHelper.resolveShop(flowerDTO);

        Flower flowerToSave = flowerMapper.toEntity(flowerDTO);

        flowerToSave.setFlowerColors(
                productCommonServiceHelper.resolveFlowerColors(
                        flowerDTO.getFlowerColors())
        );
        flowerToSave.setFlowerSorts(
                productCommonServiceHelper.resolveFlowerSorts(
                        flowerDTO.getFlowerSorts())
        );
        flowerToSave.setCountry(
                productCommonServiceHelper.resolveCountry(
                        flowerDTO.getCountry())
        );
        flowerToSave.setShop(shop);
        flowerToSave.setImage(new Image(flowerDTO.getImage().getImageUrl()));

        return flowerMapper.toDto(flowerRepository.save(flowerToSave));
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Flower flower = flowerRepository.findById(id)
                .orElseThrow(() -> {
                            logger.error("Flower with id={} not found!", id);
                            return new ResourceNotFoundException("Flower with id=" + id + " not found!");
                        }
                );

        flower.getShop().getShopProducts().remove(flower);

        flowerRepository.delete(flower);
    }

    private boolean isFlowerExistById(Long id) {
        return flowerRepository.findById(id).isPresent();
    }


    @Override
    public PageWrapper<FlowerDTO> findAll(int page, int size, SearchAndSortParamDto searchAndSortParamDto) {
        Pageable pageable = getPageable(page, size);

//        Specification<Flower> specification = getSpecification(searchAndSortParamDto);
//        Page<Flower> flowers = flowerRepository.findAll(specification, pageable);
        Page<Flower> flowers = flowerRepository.findAll(pageable);

        return
                new PageWrapper<>(
                        flowerMapper.toDtoList(flowers.toList()),
                        flowers.getTotalPages(),
                        flowers.getTotalElements());
    }

    @Override
    public PageWrapper<FlowerDTO> findAllByShopId(int page, int size, Long shopId) {
        Pageable pageable = getPageable(page, size);

        Page<Flower> flowers = flowerRepository.findAllByShopId(shopId, pageable);

        return
                new PageWrapper<>(
                        flowerMapper.toDtoList(flowers.toList()),
                        flowers.getTotalPages(),
                        flowers.getTotalElements());
    }

    private Pageable getPageable(int page, int size) {
//        Sort sort = sortType.equalsIgnoreCase("ASC") ?
//                Sort.by(sortBy).ascending() :
//                Sort.by(sortBy).descending();
        return PageRequest.of(page, size);
    }


//    private Specification<Flower> getSpecification(SearchAndSortParamDto searchAndSortParamDto) {
//
//        Specification<Flower> specification = Specification
//                .where(CompanySpecification.findByCompanyNameLike(companySearch.getOrganizationName()));
//        if (Objects.nonNull(companySearch.getLogisticDirections()) &&
//                !companySearch.getLogisticDirections().isEmpty()) {
//            specification = specification.and(CompanySpecification.findAllByTruckTypes(companySearch.getLogisticDirections()));
//        }
//
//        if (Objects.nonNull(specification) &&
//                Objects.nonNull(companySearch.getLogisticTypes()) &&
//                !companySearch.getLogisticTypes().isEmpty()) {
//            specification = specification.and(CompanySpecification.findAllByLogisticTypes(companySearch.getLogisticTypes()));
//        }
//
//        if (Objects.nonNull(specification) &&
//                Objects.nonNull(companySearch.getCarParkSize())) {
//            specification = specification.and(
//                    CompanySpecification.findByCarParkSize
//                            (companySearch.getCarParkSize().getFromCount(),
//                                    companySearch.getCarParkSize().getUntilCount()));
//        }
//
//        return specification;
//    }
}
