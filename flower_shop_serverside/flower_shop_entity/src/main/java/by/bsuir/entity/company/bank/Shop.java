package by.bsuir.entity.company.bank;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.common.Image;
import by.bsuir.entity.company.Company;
import by.bsuir.entity.company.Contacts;
import by.bsuir.entity.company.WorkingHours;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shops")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Shop extends AbstractEntity {
    
    private Contacts contacts;

    private WorkingHours workingHours;

    private Long companyId;

    private Image image;
}
