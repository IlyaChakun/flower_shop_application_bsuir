package by.bsuir.entity.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class OrderCosts {

    @Column(name = "total_amount")
    private Double totalAmount;

}
