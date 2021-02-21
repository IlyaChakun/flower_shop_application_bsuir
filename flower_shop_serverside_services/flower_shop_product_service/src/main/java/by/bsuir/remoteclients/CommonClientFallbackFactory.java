package by.bsuir.remoteclients;

import by.bsuir.payload.ResourceNotFoundException;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class CommonClientFallbackFactory implements FallbackFactory<CommonClient> {

    @Override
    public CommonClient create(Throwable cause) {

        log.error(cause.toString());
        log.error(cause.getMessage());

        int httpStatus = cause instanceof FeignException ? ((FeignException) cause).status() : 0;

        log.error("httpStatus=" + httpStatus);

        if (HttpStatus.NOT_FOUND.value() == httpStatus) {
            throw new ResourceNotFoundException(cause.getMessage());
        }

        //return new RuntimeException(body);

        return countryId -> {
            log.error("exception handler return null");
            return null;
        };

    }
}
