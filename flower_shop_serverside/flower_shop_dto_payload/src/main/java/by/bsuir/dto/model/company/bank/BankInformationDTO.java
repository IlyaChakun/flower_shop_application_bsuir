package by.bsuir.dto.model.company.bank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class BankInformationDTO {

    @NotBlank(message = "Название банка не может быть пустым")
    @Size(max = 48, message = "Название банка не более 48 символов!")
    private String bankName;

    @NotBlank(message = "IBAN банка не может быть пустым")
    @Size(max = 34, message = "IBAN банка не более 34 символов!")
    private String bankCode;//идентификациооный номер банка в системе

    @NotBlank(message = "Почтовый индекс не может быть пустым")
    @Size(max = 7, message = "Почтовый индекс  не более 7 символов!")
    private String postalCode;

    @NotBlank(message = "Адрес банка не может быть пустым")
    @Size(max = 48, message = "Адрес банка не более 48 символов!")
    private String address;

}
