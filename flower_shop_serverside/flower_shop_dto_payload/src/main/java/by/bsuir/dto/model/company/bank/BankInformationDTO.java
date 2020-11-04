package by.bsuir.dto.model.company.bank;

import by.bsuir.dto.model.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
public class BankInformationDTO extends AbstractDTO {

    private String bankName;

    private String bankCode;//идентификациооный номер банка в системе

    private String postalCode;

    private String address;

}
