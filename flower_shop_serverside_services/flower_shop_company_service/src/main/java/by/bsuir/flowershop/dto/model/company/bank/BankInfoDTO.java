package by.bsuir.flowershop.dto.model.company.bank;

import by.bsuir.flowershop.dto.model.company.ContactsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class BankInfoDTO {

    @NotBlank(message = "Название банка не может быть пустым")
    @Size(max = 48, message = "Название банка не более 48 символов!")
    private String bankName;

    @NotBlank(message = "IBAN банка не может быть пустым")
    @Size(max = 34, message = "IBAN банка не более 34 символов!")
    private String bankCode;//идентификациооный номер банка в системе

    @Valid
    private ContactsDTO contacts;

}
