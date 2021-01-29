package by.bsuir.flowershop.dto.model.company;

import by.bsuir.flowershop.dto.model.AbstractDTO;
import by.bsuir.flowershop.entity.common.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
public class CompanyDTO extends AbstractDTO {

    private Long adminId;

    @NotBlank(message = "Название организации не может быть пустым")
    @Size(max = 48, message = "Название организации не более 48 символов!")
    private String name;

    @NotBlank
    @Size(max = 512, message = "Описание для организации не более 48 символов!")
    private String description;

    @Size(max = 20, message = "Номер лицензии организации не более 48 символов!")
    private String licenceNumber;

    @Valid
    private ContactsDTO contacts;

    @Valid
    private CompanyLegalAddressDTO companyLegalAddress;

    private Image logo;

}
