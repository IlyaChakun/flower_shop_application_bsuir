package by.bsuir.service.impl;

import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.entity.common.Country;
import by.bsuir.payload.exception.ResourceNotFoundException;
import by.bsuir.repository.api.common.CountryRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommonServiceHelper {

    private static final Logger logger = LoggerFactory.getLogger(CommonServiceHelper.class);


    private final CountryRepository countryRepository;


     Country resolveCountry(CountryDTO countryDTO) {
        return countryRepository.findById(countryDTO.getId())
                .orElseThrow(() -> {
                    logger.error("Country with id={} doesn't exist!", countryDTO.getId());
                    throw new ResourceNotFoundException("Country with id=" + countryDTO.getId() + " not found!");
                });
    }


     Pageable getPageable(int page, int size) {
//        Sort sort = sortType.equalsIgnoreCase("ASC") ?
//                Sort.by(sortBy).ascending() :
//                Sort.by(sortBy).descending();
        return PageRequest.of(page, size);
    }
}
