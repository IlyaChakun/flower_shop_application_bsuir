package by.bsuir.entity.order.review;

import by.bsuir.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_reviews")
@Getter
@Setter
@NoArgsConstructor
public class OrderReview extends AbstractEntity {

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "text")
    private String text;

    @Column(name = "rating")
    private Integer rating;

}