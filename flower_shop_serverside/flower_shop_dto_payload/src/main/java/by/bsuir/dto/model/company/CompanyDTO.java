package by.bsuir.dto.model.company;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.user.ShopAdminDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private String name;

    @Size(min = 0, max = 512, message = "Описание для организации не более 512 символов!")
    private String description;

    @NotBlank(message = "Номер лицензии организации не может быть пустым")
    private String licenceNumber;

    @Valid
    private ContactsDTO contacts;

    @Valid
    private CompanyLegalAddressDTO companyLegalAddress;

    // private Image logo;

    @Valid
    private List<ShopDTO> shops = new ArrayList<>();

}
