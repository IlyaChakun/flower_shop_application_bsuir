package by.bsuir.entity.order.buynow;

import by.bsuir.entity.order.BaseOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orders_buy_now")
@NoArgsConstructor
@Getter
@Setter
public class BuyNowOrder extends BaseOrder {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

}
