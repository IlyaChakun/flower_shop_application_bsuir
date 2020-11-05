package by.bsuir.dto.model.user;

import by.bsuir.dto.model.company.CompanyDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShopAdminDTO extends AbstractUserDTO {

    @JsonIgnore
    private CompanyDTO company;

}
