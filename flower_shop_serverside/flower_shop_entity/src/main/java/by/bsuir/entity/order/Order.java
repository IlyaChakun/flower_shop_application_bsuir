package by.bsuir.entity.order;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.order.delivery.OrderDeliveryInfo;
import by.bsuir.entity.order.review.OrderReview;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
//@Inheritance(strategy = InheritanceType.JOINED)
public class Order extends AbstractEntity {

    @Column(name = "client_id")
    private Long clientId;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "close_description")
    private String closeDescription;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Column(name = "comment", length = 512)
    private String comment;

    /**
     * price\discount info (calculated automatically)
     */
    private OrderPriceInfo orderPriceInfo;


    /**
     * delivery info
     **/
    private OrderDeliveryInfo orderDeliveryInfo;


    /**
     * order detail info
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderFloristInfo orderFloristInfo;

    @OneToOne(cascade = {CascadeType.ALL})
    private OrderReview orderReview;

    /****/

    @PrePersist
    private void initOrderStatus() {
        this.setOrderStatus(OrderStatus.NEW);
    }
}
