package by.bsuir.service;


import by.bsuir.payload.ResourceNotFoundException;
import by.bsuir.payload.ServiceException;
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
