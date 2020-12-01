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
    @Size(max = 9, message = "Учетный номер плательщика не может более 9 символов!")
    private String payerAccountNumber;

    @NotBlank(message = "Расчетный счет не может быть пустым")
    @Size(max = 28, message = "Расчетный счет должен быть не более 20 символов!")
    private String checkingAccount;

    @Valid
    private BankInformationDTO bankInformation;

}
