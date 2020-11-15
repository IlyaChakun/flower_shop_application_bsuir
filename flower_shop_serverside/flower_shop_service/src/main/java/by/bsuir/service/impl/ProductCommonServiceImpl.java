package by.bsuir.service.impl;

import by.bsuir.dto.mapper.product.BouquetTypeMapperDTO;
import by.bsuir.dto.mapper.product.FlowerTypeMapperDTO;
import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.flower.FlowerTypeDTO;
import by.bsuir.repository.api.core.BouquetTypeRepository;
import by.bsuir.repository.api.core.FlowerTypeRepository;
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
    private final FlowerTypeMapperDTO flowerTypeMapperDTO;
    private final BouquetTypeRepository bouquetTypeRepository;
    private final BouquetTypeMapperDTO bouquetTypeMapperDTO;


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
}