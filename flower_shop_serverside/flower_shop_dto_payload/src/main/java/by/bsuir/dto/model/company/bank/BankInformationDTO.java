package by.bsuir.dto.model.company.bank;

import by.bsuir.dto.model.BaseAbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BankInformationDTO extends BaseAbstractDTO {

    private String bankName;

    private String bankCode;//идентификациооный номер банка в системе

    private String postalCode;

    private String address;

}
