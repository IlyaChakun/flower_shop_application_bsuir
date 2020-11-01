package by.bsuir.entity.company;

import by.bsuir.entity.common.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Table(name = "company_owner")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Company {

    @Column(name = "company_name", length = 48)
    private String companyName;

    @Column(name = "company_desciption", length = 48)
    private String companyDesciption;

    private CompanyOwner owner;

    private Contacts contacts;

    private Image logo;

    private Set<Shop> shops = new HashSet<>();


}
