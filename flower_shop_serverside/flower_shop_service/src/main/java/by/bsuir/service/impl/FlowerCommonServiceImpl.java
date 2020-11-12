package by.bsuir.service.impl;

import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.flower.FlowerTypeDTO;
import by.bsuir.service.api.BouquetTypeService;
import by.bsuir.service.api.FlowerCommonService;
import by.bsuir.service.api.FlowerTypeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlowerCommonServiceImpl implements FlowerCommonService {

    private static final Logger logger = LoggerFactory.getLogger(FlowerCommonServiceImpl.class);

    private final BouquetTypeService bouquetTypeService;
    private final FlowerTypeService flowerTypeService;


    @Override
    public PageWrapper<FlowerTypeDTO> findAllFlowerTypes(int page, Integer size) {
        return flowerTypeService.findAll(page, size);
    }


    @Override
    public PageWrapper<BouquetTypeDTO> findAllFlowerBouquetTypes(int page, Integer size) {
        return bouquetTypeService.findAll(page, size);
    }

}