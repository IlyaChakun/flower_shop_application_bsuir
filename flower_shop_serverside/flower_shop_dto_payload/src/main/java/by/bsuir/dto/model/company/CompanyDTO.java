package by.bsuir.dto.model.company;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.user.ShopAdminDTO;
import by.bsuir.entity.company.CompanyLegalAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@Getter
@Setter
public class CompanyDTO extends AbstractDTO {

    private ShopAdminDTO shopAdmin;

    private String name;

    private String description;

    private String licenceNumber;

    private ContactsDTO contacts;

    private CompanyLegalAddress companyLegalAddress;

    // private Image logo;

    private List<ShopDTO> shops;


}
