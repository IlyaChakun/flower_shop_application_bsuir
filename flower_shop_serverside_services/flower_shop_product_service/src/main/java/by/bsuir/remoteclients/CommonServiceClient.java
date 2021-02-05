package by.bsuir.remoteclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "common-service")
public interface CommonServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/common/countries/{countryId}")
    CountryDTO findById(@PathVariable("countryId") Long countryId);

}
