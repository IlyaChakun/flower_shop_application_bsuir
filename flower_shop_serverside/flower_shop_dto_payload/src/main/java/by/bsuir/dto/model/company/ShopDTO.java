package by.bsuir.dto.model.company;

import by.bsuir.dto.model.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ShopDTO extends AbstractDTO {

    private ContactsDTO contacts;
    private WorkingHoursDTO workingHours;
    private CompanyDTO company;
//    private List<AbstractFlowerProduct> shopProducts = new ArrayList<>();
}
