package by.bsuir.service.api;


import by.bsuir.dto.model.PageWrapper;
import by.bsuir.dto.model.product.bouquet.BouquetTypeDTO;
import by.bsuir.dto.model.product.flower.FlowerTypeDTO;

public interface FlowerCommonService {

    //TODO а зачем тут пагинация..
    PageWrapper<FlowerTypeDTO> findAllFlowerTypes(int page, Integer size);

    PageWrapper<BouquetTypeDTO> findAllFlowerBouquetTypes(int page, Integer size);
}
