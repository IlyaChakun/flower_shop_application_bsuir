package by.bsuir.entity.company.bank;

import by.bsuir.entity.company.Contacts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class BankInfo {

    @Column(name = "bank_name", length = 48)
    private String bankName;

    @Column(name = "bank_code", length = 34)
    private String bankCode;//идентификациооный номер банка в системе

   // private Contacts contacts;

}
