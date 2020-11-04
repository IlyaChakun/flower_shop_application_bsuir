package by.bsuir.dto.model.company.bank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BankInformationDTO {

    private String bankName;
    private String bankCode;//идентификациооный номер банка в системе
    private String postalCode;
    private String address;

}
