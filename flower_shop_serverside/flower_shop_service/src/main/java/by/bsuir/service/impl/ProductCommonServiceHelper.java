package by.bsuir.service.impl;

import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.dto.model.product.AbstractFlowerProductDTO;
import by.bsuir.dto.model.product.common.FlowerColorDTO;
import by.bsuir.dto.model.product.common.FlowerSortDTO;
import by.bsuir.entity.common.Country;
import by.bsuir.entity.company.Shop;
import by.bsuir.entity.product.common.FlowerColor;
import by.bsuir.entity.product.common.FlowerSort;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.payload.exception.ServiceException;
import by.bsuir.repository.api.CountryRepository;
import by.bsuir.repository.api.FlowerColorRepository;
import by.bsuir.repository.api.FlowerSortRepository;
import by.bsuir.repository.api.ShopRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductCommonServiceHelper {

    private static final Logger logger = LoggerFactory.getLogger(FlowerServiceImpl.class);

    private final ShopRepository shopRepository;

    private final FlowerColorRepository flowerColorRepository;
    private final FlowerSortRepository flowerSortRepository;
    private final CountryRepository countryRepository;


    Shop resolveShop(AbstractFlowerProductDTO flowerProductDTO) {
        return shopRepository.findById(flowerProductDTO.getShop().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop with id=" + flowerProductDTO.getShop().getId() + " not found!"));
    }


    List<FlowerColor> resolveFlowerColors(List<FlowerColorDTO> flowerColorDTOList) {

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
    }

    List<FlowerSort> resolveFlowerSorts(List<FlowerSortDTO> flowerSortDTOList) {

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
    }

    Country resolveCountry(CountryDTO countryDTO) {
        return countryRepository.findById(countryDTO.getId())
                .orElseThrow(() -> {
                    logger.error("Country with id={} doesn't exist!", countryDTO.getId());
                    throw new ResourceNotFoundException("Country with id=" + countryDTO.getId() + " not found!");
                });
    }

}
