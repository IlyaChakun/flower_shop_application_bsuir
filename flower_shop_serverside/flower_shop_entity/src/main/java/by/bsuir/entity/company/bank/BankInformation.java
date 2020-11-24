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

    @Column(name = "bank_name", length = 48)
    private String bankName;

    @Column(name = "bank_code", length = 34)
    private String bankCode;//идентификациооный номер банка в системе

    @Column(name = "postal_code", length = 7)
    private String postalCode;

    @Column(name = "bank_address", length = 48)
    private String address;

}
