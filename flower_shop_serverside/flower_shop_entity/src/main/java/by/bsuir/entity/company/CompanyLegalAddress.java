package by.bsuir.entity.company;

import by.bsuir.entity.company.bank.BankInformation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class CompanyLegalAddress {

    @Column(name = "payer_account_number", length = 9)
    private String payerAccountNumber;

    @Column(name = "checking_account", length = 28)
    private String checkingAccount;

    private BankInformation bankInformation;

}
