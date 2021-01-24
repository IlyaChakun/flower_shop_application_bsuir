package by.bsuir.entity.order.usual;

import by.bsuir.entity.order.BaseOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orders_usual")
@NoArgsConstructor
@Getter
@Setter
public class UsualOrder extends BaseOrder {

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "order_review_id")
    private Long orderReviewId;

}