package by.bsuir.entity.company.bank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class BankInformation {

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_code")
    private String bankCode;//идентификациооный номер банка в системе

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "address")
    private String address;

}
