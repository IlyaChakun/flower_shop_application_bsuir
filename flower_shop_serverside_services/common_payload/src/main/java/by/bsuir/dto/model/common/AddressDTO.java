package by.bsuir.dto.model.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {

    private RegionDTO region;

    private CityDTO city;

    private String address;

}
