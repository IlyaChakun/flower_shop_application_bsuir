package by.bsuir.dto.model.company;

import by.bsuir.dto.model.company.bank.BankInformationDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CompanyLegalAddressDTO {

    private String payerAccountNumber;
    private String checkingAccount;
    private BankInformationDTO bankInformation;

}
