package by.bsuir.entity.company;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.common.Image;
import by.bsuir.entity.company.Company;
import by.bsuir.entity.company.Contacts;
import by.bsuir.entity.company.WorkingHours;
import by.bsuir.entity.product.AbstractFlowerProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shops")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Shop extends AbstractEntity {
    
    private Contacts contacts;

    private WorkingHours workingHours;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Company company;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AbstractFlowerProduct> shopProducts = new ArrayList<>();

    private Image image;

}
