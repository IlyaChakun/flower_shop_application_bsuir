package by.bsuir.dto.model.shop;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.common.ImageDTO;
import by.bsuir.dto.model.company.ContactsDTO;
import by.bsuir.dto.model.company.WorkingHoursDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;


@Getter
@Setter
@NoArgsConstructor
public class ShopDTO extends AbstractDTO {

    @Valid
    private ContactsDTO contacts;

    @Valid
    private WorkingHoursDTO workingHours;

    private Long companyId;

    private ImageDTO image;
}
