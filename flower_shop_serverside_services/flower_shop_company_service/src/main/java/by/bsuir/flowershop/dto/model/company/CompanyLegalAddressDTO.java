package by.bsuir.flowershop.dto.model.company;

import by.bsuir.flowershop.dto.model.company.bank.BankInfoDTO;
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
    private String taxPayerIdentificationNumber;

    @NotBlank(message = "Расчетный счет не может быть пустым")
    @Size(max = 20, message = "Расчетный счет должен быть не более 20 символов!")
    private String checkingAccount;

    @Valid
    private BankInfoDTO bankInfo;

}
