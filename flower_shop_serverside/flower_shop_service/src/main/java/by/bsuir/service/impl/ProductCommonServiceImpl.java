package by.bsuir.service.impl;

import by.bsuir.dto.mapper.common.CountryMapperDTO;
import by.bsuir.dto.mapper.product.*;
import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.flower.FlowerTypeDTO;
import by.bsuir.repository.api.*;
import by.bsuir.service.api.ProductCommonService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCommonServiceImpl implements ProductCommonService {

    private static final Logger logger = LoggerFactory.getLogger(ProductCommonServiceImpl.class);

    private final FlowerTypeRepository flowerTypeRepository;
    private final BouquetTypeRepository bouquetTypeRepository;
    private final FlowerTypeMapperDTO flowerTypeMapperDTO;
    private final BouquetTypeMapperDTO bouquetTypeMapperDTO;

    private final FlowerColorRepository flowerColorRepository;
    private final FlowerSortRepository flowerSortRepository;
    private final FlowerLengthCostRepository flowerLengthCostRepository;
    private final CountryRepository countryRepository;

    private final FlowerColorMapperDTO flowerColorMapperDTO;
    private final FlowerSortMapperDTO flowerSortMapperDTO;
    private final FlowerLengthCostMapperDTO flowerLengthCostMapperDTO;
    private final CountryMapperDTO countryMapperDTO;


    @Override
    public List<FlowerTypeDTO> findAllFlowerTypes() {
        logger.info("Searching all flower types");
        return flowerTypeMapperDTO.toDtoList(flowerTypeRepository.findAll());
    }


    @Override
    public List<BouquetTypeDTO> findAllFlowerBouquetTypes() {
        logger.info("Searching all flower bouquet types");
        return bouquetTypeMapperDTO.toDtoList(bouquetTypeRepository.findAll());
    }

//    @Override
//    public Optional<FlowerColorDTO> findFlowerColorById(Long id) {
//        logger.info("Searching flower color by id={}", id);
//        return flowerColorRepository.findById(id).map(flowerColorMapperDTO::toDto);
//    }
//
//    @Override
//    public FlowerColorDTO addFlowerColor(String colorName) {
//        logger.info("Creating new flower color={}", colorName);
//        FlowerColor flowerColor = new FlowerColor();
//        flowerColor.setColorName(colorName);
//        return flowerColorMapperDTO.toDto(flowerColorRepository.save(flowerColor));
//    }
}