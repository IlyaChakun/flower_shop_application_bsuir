package by.bsuir.service.api;

import by.bsuir.dto.model.common.CountryDTO;
import by.bsuir.dto.model.common.ProductLengthDTO;
import by.bsuir.dto.model.order.delivery.DeliveryTypeDTO;

import java.util.List;

public interface CommonService {

    List<ProductLengthDTO> findAllProductLengths();

    List<CountryDTO> findAllCountries();

    List<DeliveryTypeDTO> findAllDeliveryTypes();

}
