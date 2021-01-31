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
public class BankInfo {

    @Column(name = "bank_name", length = 48)
    private String bankName;

    @Column(name = "bank_code", length = 34)
    private String bankCode;//идентификациооный номер банка в системе

    // private Contacts contacts; //надо либо убирать либо переименовывать тк везде embeddable и два поля address одинаковых буудт

}
