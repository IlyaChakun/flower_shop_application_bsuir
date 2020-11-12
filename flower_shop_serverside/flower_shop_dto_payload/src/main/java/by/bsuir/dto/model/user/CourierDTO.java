package by.bsuir.dto.model.user;

import by.bsuir.entity.deliveryroute.DeliveryRoute;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CourierDTO extends AbstractUserDTO {

    @Valid
    private List<DeliveryRoute> deliveryRoutes = new ArrayList<>();

}
