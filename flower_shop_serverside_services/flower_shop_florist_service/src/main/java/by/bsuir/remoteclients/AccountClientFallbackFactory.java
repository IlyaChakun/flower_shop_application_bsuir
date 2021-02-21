package by.bsuir.remoteclients;

import by.bsuir.payload.ServiceException;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class AccountClientFallbackFactory implements FallbackFactory<AccountClient> {

    @Override
    public AccountClient create(Throwable cause) {

        log.error(cause.toString());
        log.error(cause.getMessage());

        int httpStatus = cause instanceof FeignException ? ((FeignException) cause).status() : 0;

        log.error("httpStatus=" + httpStatus);

        throw new ServiceException(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "account_service_fetch_error",
                "Can`t complete fetch! Try again later"
        );

    }
}
