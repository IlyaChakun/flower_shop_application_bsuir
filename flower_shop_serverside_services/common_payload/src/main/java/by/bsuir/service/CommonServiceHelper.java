package by.bsuir.service;


import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.entity.common.Country;
import by.bsuir.payload.ResourceNotFoundException;
import by.bsuir.payload.ServiceException;
import by.bsuir.repository.api.common.CountryRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CommonServiceHelper {

    private static final Logger logger = LoggerFactory.getLogger(CommonServiceHelper.class);



    private final CountryRepository countryRepository;


    public Country resolveCountry(CountryDTO countryDTO) {
        return countryRepository.findById(countryDTO.getId())
                .orElseThrow(() -> {
                    logger.error("Country with id={} doesn't exist!", countryDTO.getId());
                    throw new ResourceNotFoundException("Country with id=" + countryDTO.getId() + " not found!");
                });
    }

    public Pageable getPageable(int page, int size) {
//        Sort sort = sortType.equalsIgnoreCase("ASC") ?
//                Sort.by(sortBy).ascending() :
//                Sort.by(sortBy).descending();
        return PageRequest.of(page, size);
    }

    public void checkIdOrThrowException(final Long id,
                                        final String error,
                                        final String message) {
        if (Objects.isNull(id)) {
            logger.error("Resolve error: type=" + error + ",  message= " + message + ",  resourceId= " + id);
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                    error,
                    message);
        }
    }

}
