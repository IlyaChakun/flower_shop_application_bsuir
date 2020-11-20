package by.bsuir.service.api;


import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.common.FlowerColorDTO;
import by.bsuir.dto.model.product.common.FlowerSortDTO;
import by.bsuir.dto.model.product.flower.FlowerTypeDTO;

import java.util.List;

public interface ProductCommonService {

    List<FlowerTypeDTO> findAllFlowerTypes();

    List<BouquetTypeDTO> findAllFlowerBouquetTypes();

    List<CountryDTO> findAllCountries();

    List<FlowerSortDTO> findAllFlowerSorts();

    List<FlowerColorDTO> findAllFlowerColors();
}
