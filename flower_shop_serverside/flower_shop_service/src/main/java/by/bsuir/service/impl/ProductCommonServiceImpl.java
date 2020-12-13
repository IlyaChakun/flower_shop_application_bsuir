package by.bsuir.service.impl;

import by.bsuir.dto.mapper.common.CountryMapperDTO;
import by.bsuir.dto.mapper.product.*;
import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.common.FlowerColorDTO;
import by.bsuir.dto.model.product.common.FlowerLengthCostDTO;
import by.bsuir.dto.model.product.common.FlowerSortDTO;
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
    private final CountryRepository countryRepository;
    private final FlowerLengthCostRepository flowerLengthCostRepository;

    private final FlowerColorMapperDTO flowerColorMapperDTO;
    private final FlowerSortMapperDTO flowerSortMapperDTO;
    private final CountryMapperDTO countryMapperDTO;
    private final FlowerLengthCostMapperDTO flowerLengthCostMapperDTO;


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

    @Override
    public List<CountryDTO> findAllCountries() {
        logger.info("Searching all countries types");
        return countryMapperDTO.toDtoList(countryRepository.findAll());
    }

    @Override
    public List<FlowerSortDTO> findAllFlowerSorts() {
        logger.info("Searching all flower sort types");
        return flowerSortMapperDTO.toDtoList(flowerSortRepository.findAll());
    }

    @Override
    public List<FlowerColorDTO> findAllFlowerColors() {
        logger.info("Searching all flower sort types");
        return flowerColorMapperDTO.toDtoList(flowerColorRepository.findAll());
    }

    @Override
    public List<FlowerLengthCostDTO> findAllFlowerLengthCosts() {
        logger.info("Searching all flower length costs");
        return flowerLengthCostMapperDTO.toDtoList(flowerLengthCostRepository.findAll());
    }
}
