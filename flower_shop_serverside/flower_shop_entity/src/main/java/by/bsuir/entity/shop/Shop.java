package by.bsuir.entity.shop;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.common.Image;
import by.bsuir.entity.company.Contacts;
import by.bsuir.entity.company.WorkingHours;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shops")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Shop extends AbstractEntity {

    @Column(name = "company_id")
    private Long companyId;

    private Contacts contacts;

    private WorkingHours workingHours;

    private Image image;
}
