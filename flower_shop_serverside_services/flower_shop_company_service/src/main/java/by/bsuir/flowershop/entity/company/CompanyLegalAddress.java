package by.bsuir.flowershop.entity.company;

import by.bsuir.flowershop.entity.company.bank.BankInfo;
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

    @Column(name = "tin", length = 9)
    private String taxPayerIdentificationNumber;

    @Column(name = "checking_account", length = 20)
    private String checkingAccount;

    private BankInfo bankInfo;

}
