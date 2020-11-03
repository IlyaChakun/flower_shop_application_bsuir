package by.bsuir.dto.model.company;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.user.ShopAdminDTO;
import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.company.CompanyLegalAddress;
import by.bsuir.entity.company.Contacts;
import by.bsuir.entity.company.Shop;
import by.bsuir.entity.user.ShopAdmin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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

    private Set<Shop> shops = new HashSet<>();


}
