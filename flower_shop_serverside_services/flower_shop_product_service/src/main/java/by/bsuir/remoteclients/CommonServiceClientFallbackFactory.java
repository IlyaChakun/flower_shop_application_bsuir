package by.bsuir.remoteclients;

import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class UserClientFallbackFactory implements FallbackFactory<CommonServiceClient> {

    private static final Logger logger = LoggerFactory.getLogger(UserClientFallbackFactory.class);


    @Override
    public CommonServiceClient create(Throwable cause) {


        String httpStatus = cause instanceof FeignException ? Integer.toString(((FeignException) cause).status()) : "";

        return countryId -> {
            logger.error(httpStatus);
            System.out.println("exception handler");
            return null;
        };
    }
}
