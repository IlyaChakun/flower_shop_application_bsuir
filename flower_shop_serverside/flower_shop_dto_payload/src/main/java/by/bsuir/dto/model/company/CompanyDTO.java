package by.bsuir.dto.model.company;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.user.ShopAdminDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CompanyDTO extends AbstractDTO {

    private ShopAdminDTO shopAdmin;

    private String name;

    private String description;

    private String licenceNumber;

    private ContactsDTO contacts;

    private CompanyLegalAddressDTO companyLegalAddress;

    // private Image logo;

    private List<ShopDTO> shops = new ArrayList<>();


}
