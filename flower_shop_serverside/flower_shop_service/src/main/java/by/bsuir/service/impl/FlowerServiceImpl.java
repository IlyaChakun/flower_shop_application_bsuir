package by.bsuir.service.impl;

import by.bsuir.dto.mapper.product.FlowerMapperDTO;
import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.SearchAndSortParamDto;
import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.dto.model.product.common.FlowerColorDTO;
import by.bsuir.dto.model.product.common.FlowerSortDTO;
import by.bsuir.dto.model.product.flower.FlowerDTO;
import by.bsuir.entity.common.Country;
import by.bsuir.entity.company.Shop;
import by.bsuir.entity.product.common.FlowerColor;
import by.bsuir.entity.product.common.FlowerSort;
import by.bsuir.entity.product.flower.Flower;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.*;
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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlowerServiceImpl implements FlowerService {

    private static final Logger logger = LoggerFactory.getLogger(FlowerServiceImpl.class);

    private final FlowerRepository flowerRepository;
    private final FlowerMapperDTO flowerMapper;

    private final FlowerColorRepository flowerColorRepository;
    private final FlowerSortRepository flowerSortRepository;
    private final CountryRepository countryRepository;
    private final ShopRepository shopRepository;

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

        Shop shop = shopRepository.findById(flowerDTO.getShop().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id=" + flowerDTO.getShop().getId() + " not found!"));

        Flower flower = flowerMapper.toEntity(flowerDTO);
        flower.setFlowerColors(resolveFlowerColors(flowerDTO.getFlowerColors()));
        flower.setFlowerSorts(resolveFlowerSorts(flowerDTO.getFlowerSorts()));
        flower.setCountry(resolveCountry(flowerDTO.getCountry()));
        //
        flower.setShop(shop);
        shop.getShopProducts().add(flower);
        //
        Flower savedFlower = flowerRepository.save(flower);

        logger.info("Saved Flower with id={} and type={}", flower.getId(), flower.getFlowerType());

        return flowerMapper.toDto(savedFlower);
    }

    private List<FlowerColor> resolveFlowerColors(List<FlowerColorDTO> flowerColorDTOList) {

        return flowerColorDTOList.stream()
                .map(flowerColorDTO -> {
                            if (Objects.isNull(flowerColorDTO.getId())) {
                                throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                                        "flower_color_empty_id_error",
                                        "Color id can`t be empty");
                            }
                            return flowerColorRepository.findById(flowerColorDTO.getId())
                                    .orElseThrow(() ->
                                            new ResourceNotFoundException("Color with id=" + flowerColorDTO.getId() + " not found!"));
                        }
                )
                .collect(Collectors.toList());

//        return flowerColorDTOList.stream()
//                .map(flowerColorDTO -> flowerColorRepository.findById(flowerColorDTO.getId())
//                        .orElseGet(() -> flowerColorRepository.save(new FlowerColor(flowerColorDTO.getColorName()))))
//                .collect(Collectors.toList());
    }

    private List<FlowerSort> resolveFlowerSorts(List<FlowerSortDTO> flowerSortDTOList) {

        return flowerSortDTOList.stream()
                .map(flowerSortDTO -> {
                    if (Objects.isNull(flowerSortDTO.getId())) {
                        throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                                "flower_sort_empty_id_error",
                                "Sort id can`t be empty");
                    }
                    return flowerSortRepository.findById(flowerSortDTO.getId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException("Flower sort with id=" + flowerSortDTO.getId() + " not found!"));
                })
                .collect(Collectors.toList());

//        return flowerSortDTOList.stream()
//                .map(flowerSortDTO -> flowerSortRepository.findById(flowerSortDTO.getId())
//                        .orElseGet(() -> flowerSortRepository.save(new FlowerSort(flowerSortDTO.getSortNameRu(), flowerSortDTO.getSortNameEn()))))
//                .collect(Collectors.toList());
    }

//    private List<FlowerLengthCost> resolveFlowerLengthCost(List<FlowerLengthCostDTO> flowerLengthCostDTOS) {
//
//        return flowerLengthCostDTOS.stream()
//                .map(flowerLengthCostDTO -> {
//
//                    return flowerLengthCostRepository.findById(flowerLengthCostDTO.getId())
//                            .orElseThrow(() ->
//                                    new ResourceNotFoundException("Flower length cost with id=" + flowerLengthCostDTO.getId() + " not found!"));
//                })
//                .collect(Collectors.toList());
//
////        return flowerSortDTOList.stream()
////                .map(flowerSortDTO -> flowerSortRepository.findById(flowerSortDTO.getId())
////                        .orElseGet(() -> flowerSortRepository.save(new FlowerSort(flowerSortDTO.getSortNameRu(), flowerSortDTO.getSortNameEn()))))
////                .collect(Collectors.toList());
//    }


    private Country resolveCountry(CountryDTO countryDTO) {
        return countryRepository.findById(countryDTO.getId())
                .orElseThrow(() -> {
                    logger.error("Country with id={} doesn't exist!", countryDTO.getId());
                    throw new ResourceNotFoundException("Country with id=" + countryDTO.getId() + " not found!");
                });
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

        Flower flowerToSave = flowerMapper.toEntity(flowerDTO);

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
