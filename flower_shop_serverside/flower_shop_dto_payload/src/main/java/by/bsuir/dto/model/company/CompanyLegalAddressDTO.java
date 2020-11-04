package by.bsuir.dto.model.company;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.company.bank.BankInformationDTO;
import by.bsuir.entity.company.bank.BankInformation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Getter
@Setter
@NoArgsConstructor
public class CompanyLegalAddressDTO extends AbstractDTO {

    private String payerAccountNumber;

    private String checkingAccount;

    private BankInformationDTO bankInformation;

}
