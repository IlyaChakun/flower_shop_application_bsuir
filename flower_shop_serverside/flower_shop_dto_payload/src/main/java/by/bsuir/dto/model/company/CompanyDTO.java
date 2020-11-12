package by.bsuir.dto.model.company;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.user.ShopAdminDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CompanyDTO extends AbstractDTO {

    @Valid
    private ShopAdminDTO shopAdmin;

    @NotBlank(message = "Название организации не может быть пустым")
    @Size(max = 48, message = "Название организации не более 48 символов!")
    private String name;

    @NotBlank
    @Size(max = 48, message = "Описание для организации не более 48 символов!")
    private String description;

    @Size(max = 48, message = "Номер лицензии организации не более 48 символов!")
    private String licenceNumber;

    @Valid
    private ContactsDTO contacts;

    @Valid
    private CompanyLegalAddressDTO companyLegalAddress;

    // private Image logo;

    @Valid
    private List<ShopDTO> shops = new ArrayList<>();

}
