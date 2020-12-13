package by.bsuir.entity.ordernotification;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.company.Company;
import by.bsuir.entity.company.Shop;
import by.bsuir.entity.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_notifications")
@Getter
@Setter
@NoArgsConstructor
public class OrderNotification extends AbstractEntity {

    @OneToOne
    private Company company;

    @OneToOne
    private Shop shop; //магаз который выполняет

    @OneToOne
    private Order order; //сам заказ

}
