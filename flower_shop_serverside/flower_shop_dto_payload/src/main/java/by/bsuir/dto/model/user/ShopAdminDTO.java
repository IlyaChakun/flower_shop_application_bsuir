package by.bsuir.dto.model.user;

import by.bsuir.dto.model.company.CompanyDTO;
import by.bsuir.entity.company.Company;
import by.bsuir.entity.user.AbstractUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
public class ShopAdminDTO extends AbstractUserDTO {

    @JsonIgnore
    private CompanyDTO company;

}
