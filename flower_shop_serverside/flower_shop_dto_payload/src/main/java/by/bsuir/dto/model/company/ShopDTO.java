package by.bsuir.dto.model.company;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.entity.product.AbstractFlowerProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ShopDTO extends AbstractDTO {

    private ContactsDTO contacts;
    private WorkingHoursDTO workingHours;
    private CompanyDTO company;
//    private List<AbstractFlowerProduct> shopProducts = new ArrayList<>();
}
