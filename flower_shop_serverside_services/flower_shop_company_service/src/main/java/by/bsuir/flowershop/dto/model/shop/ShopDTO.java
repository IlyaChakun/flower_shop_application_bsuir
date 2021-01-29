package by.bsuir.flowershop.dto.model.shop;

import by.bsuir.flowershop.dto.model.AbstractDTO;
import by.bsuir.flowershop.dto.model.common.ImageDTO;
import by.bsuir.flowershop.dto.model.company.ContactsDTO;
import by.bsuir.flowershop.dto.model.company.WorkingHoursDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class ShopDTO extends AbstractDTO {

    @NotNull(message = "Company id must be set")
    private Long companyId;

    @Valid
    private ContactsDTO contacts;

    @Valid
    private WorkingHoursDTO workingHours;

    private ImageDTO image;

}
