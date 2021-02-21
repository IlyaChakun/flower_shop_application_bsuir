package by.bsuir.remoteclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "common-service")
public interface CommonClient {

    @GetMapping("/common/countries/{countryId}")
    CountryDTO findById(@PathVariable("countryId") Long countryId);

}
