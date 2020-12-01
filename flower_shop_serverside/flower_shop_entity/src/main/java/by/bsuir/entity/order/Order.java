package by.bsuir.entity.order;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.user.Client;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Column(name = "unique_id")
    private String uniqueId;//для понтов типо униклаьный ид хз зачем пригодиться

    @Column(name = "comment", length = 512)
    private String comment;

    @ManyToOne(cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    private Client client;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "address")
    private String address;

    @Column(name = "floor_number")
    private Integer floorNumber;

    @Column(name = "entrance_number")
    private Integer entranceNumber;//номер подъезда

    @Column(name="shop_id")
    private Long shopId;


    @PrePersist
    protected void orderPreInit() {
        this.uniqueId = java.util.UUID.randomUUID().toString();
    }

    @PreUpdate
    protected void orderPreUpdate() {
        if (Objects.nonNull(orderStatus) &&
                orderStatus.getStatusName().equals(OrderStatus.COMPLETED.getStatusName())) {

        }
    }

}
