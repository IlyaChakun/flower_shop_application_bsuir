package by.bsuir.dto.model.order.review;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * ОТЗЫВЫ
 */
@Entity
@Table(name = "transportation_reviews")
@Getter
@Setter
@NoArgsConstructor
public class OrderReviewDTO extends AbstractEntity {

    @Column(name = "comment", nullable = false, length = 512)
    private String comment;

    @Column(name = "rate", nullable = false)
    private Short rate;

    @Column(name = "comment_date", nullable = false)
    private LocalDateTime commentDate;

    @OneToOne
    private Order transportOrder;

    @PrePersist
    protected void transportReviewPreInit() {
        this.commentDate = LocalDateTime.now();
    }

}
