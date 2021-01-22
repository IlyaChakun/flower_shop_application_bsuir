package by.bsuir.entity.order;

import by.bsuir.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order extends AbstractEntity {

    @Column(name = "customer_id")
    private Long customerId;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Column(name = "comment", length = 512)
    private String comment;

    @Column(name = "total_amount")
    private Double totalAmount;

    /**
     * delivery info
     **/
    @Column(name = "address")
    private String address;

    @Column(name = "floor_number")
    private Integer floorNumber;

    @Column(name = "entrance_number")
    private Integer entranceNumber;//номер подъезда

    /****/

    @PrePersist
    private void initOrderStatus() {
        this.setOrderStatus(OrderStatus.NEW);
    }

}