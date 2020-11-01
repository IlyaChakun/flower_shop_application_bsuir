package by.bsuir.entity.user;

import by.bsuir.entity.deliveryroute.DeliveryRoute;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "couriers")
@Getter
@Setter
@NoArgsConstructor
public class Courier extends AbstractUser {

    @OneToMany(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            },
            fetch = FetchType.LAZY,
            mappedBy = "courier")
    private List<DeliveryRoute> deliveryRoutes = new ArrayList<>();

}
