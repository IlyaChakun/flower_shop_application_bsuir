package by.bsuir.entity.company;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.common.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

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
