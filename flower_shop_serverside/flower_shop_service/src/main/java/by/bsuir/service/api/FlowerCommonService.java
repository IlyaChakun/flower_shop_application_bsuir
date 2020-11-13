package by.bsuir.service.api;


import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.flower.FlowerTypeDTO;

import java.util.List;

public interface FlowerCommonService {

    //TODO а зачем тут пагинация..
    List<FlowerTypeDTO> findAllFlowerTypes();

    List<BouquetTypeDTO> findAllFlowerBouquetTypes();
}
