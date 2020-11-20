package by.bsuir.entity.company;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.user.ShopAdmin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Company extends AbstractEntity {

    @OneToOne
    private ShopAdmin shopAdmin;

    @Column(name = "name", length = 48)
    private String name;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "licence_number", length = 20)
    private String licenceNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Contacts contacts;

    private CompanyLegalAddress companyLegalAddress;

    // private Image logo;

    @OneToMany(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            },
            fetch = FetchType.LAZY,
            mappedBy = "company")
    private Set<Shop> shops = new HashSet<>();

}
