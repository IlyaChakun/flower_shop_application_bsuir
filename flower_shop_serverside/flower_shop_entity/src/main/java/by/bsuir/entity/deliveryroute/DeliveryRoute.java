package by.bsuir.entity.deliveryroute;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.user.Courier;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "delivery_routes")
@Getter
@Setter
@NoArgsConstructor
public class DeliveryRoute extends AbstractEntity {

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    private Courier courier;

    @Column(name = "origin")
    private String origin;//адрес магазина

    @OneToMany
    private List<DeliveryRouteSegment> deliveryRouteSegments = new ArrayList<>();


}
