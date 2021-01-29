package by.bsuir.flowershop.entity.shop;

import by.bsuir.flowershop.entity.AbstractEntity;
import by.bsuir.flowershop.entity.common.Image;
import by.bsuir.flowershop.entity.company.Contacts;
import by.bsuir.flowershop.entity.company.WorkingHours;
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
