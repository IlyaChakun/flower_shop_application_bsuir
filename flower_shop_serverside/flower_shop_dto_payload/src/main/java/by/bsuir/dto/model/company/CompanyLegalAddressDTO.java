package by.bsuir.dto.model.company;

import by.bsuir.dto.model.BaseAbstractDTO;
import by.bsuir.dto.model.company.bank.BankInformationDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class CompanyLegalAddressDTO extends BaseAbstractDTO {

    private String payerAccountNumber;

    private String checkingAccount;

    private BankInformationDTO bankInformation;

}
