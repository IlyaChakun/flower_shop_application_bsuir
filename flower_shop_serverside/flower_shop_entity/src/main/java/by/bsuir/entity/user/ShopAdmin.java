package by.bsuir.entity.user;

import by.bsuir.entity.company.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shop_admins")
@Getter
@Setter
@NoArgsConstructor
public class ShopAdmin extends AbstractUser {

    @OneToOne
    private Company company;
}
