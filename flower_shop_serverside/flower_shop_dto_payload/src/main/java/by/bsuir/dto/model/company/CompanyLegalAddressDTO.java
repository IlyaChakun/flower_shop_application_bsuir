package by.bsuir.dto.model.company;

import by.bsuir.dto.model.company.bank.BankInformationDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
public class CompanyLegalAddressDTO {

    @NotBlank(message = "Учетный номер плательщика не может быть пустым")
    private String payerAccountNumber;

    @Size(min = 26, max = 28, message = "Расчетный счет должен быть не менее 26 и не более 28 символов!")
    private String checkingAccount;

    @Valid
    private BankInformationDTO bankInformation;

}
