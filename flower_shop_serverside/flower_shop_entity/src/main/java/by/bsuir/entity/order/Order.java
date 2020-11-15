package by.bsuir.entity.order;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.product.AbstractFlowerProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order extends AbstractEntity {

    @Column(name = "unique_id")
    private String uniqueId;//для понтов типо униклаьный ид хз зачем пригодиться

    @Column(name = "comment", length = 512)
    private String comment;

//    @OneToMany
//    private Set<AbstractFlowerProduct> orderProducts = new HashSet<>();

    @OneToMany
    private Set<OrderProduct> orderProducts = new HashSet<>();

    private OrderInfo orderInfo;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @PrePersist
    protected void orderPreInit() {
        this.uniqueId = java.util.UUID.randomUUID().toString();
    }

    @PreUpdate
    protected void orderPreUpdate() {
        if (Objects.nonNull(orderStatus) &&
                orderStatus.getStatusName().equals(OrderStatus.COMPLETED.getStatusName())) {
            this.orderInfo.setDateOfComplete(LocalDateTime.now());
        }
    }

}
