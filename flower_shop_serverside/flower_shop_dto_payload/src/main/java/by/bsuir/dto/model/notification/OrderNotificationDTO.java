package by.bsuir.dto.model.notification;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.dto.model.company.ShopDTO;
import by.bsuir.dto.model.order.OrderDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class OrderNotificationDTO extends AbstractDTO {

    private CompanyDTO company;

    private ShopDTO shop;

    private OrderDTO order;

}
