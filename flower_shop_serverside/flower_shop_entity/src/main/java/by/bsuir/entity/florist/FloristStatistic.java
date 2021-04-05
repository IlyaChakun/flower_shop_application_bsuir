package by.bsuir.entity.florist;

import by.bsuir.entity.BaseAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "florists_statistics")
@NoArgsConstructor
@Getter
@Setter
public class FloristStatistic extends BaseAbstractEntity {

    @Column(name = "completed_orders_count")
    private Integer completedOrdersCount;

    @Column(name = "florist_rating_sum")
    private Double floristRatingSum;

    @Column(name = "florist_rating")
    private Double floristRating;

    @PreUpdate
    private void calculateRating() {
        this.setFloristRating(
                this.getFloristRatingSum() / this.getCompletedOrdersCount()
        );
    }
}
