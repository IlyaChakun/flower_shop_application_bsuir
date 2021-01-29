package by.bsuir.flowershop.entity.company;

import by.bsuir.flowershop.entity.AbstractEntity;
import by.bsuir.flowershop.entity.common.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Company extends AbstractEntity {

   private Long adminId;

    @Column(name = "name", length = 48)
    private String name;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "licence_number", length = 20)
    private String licenceNumber;

    private Contacts contacts;

    private CompanyLegalAddress companyLegalAddress;

    private Image logo;

}
