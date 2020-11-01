package by.bsuir.entity.company;

import by.bsuir.entity.user.AbstractUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;

@Table(name = "company_owner")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyOwner extends AbstractUser{


}
