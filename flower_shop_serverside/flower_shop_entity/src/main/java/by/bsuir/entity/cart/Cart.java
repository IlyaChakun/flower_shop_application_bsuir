package by.bsuir.entity.cart;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.product.common.FlowerLengthCost;
import by.bsuir.entity.user.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@NoArgsConstructor
@Getter
@Setter
public class Cart extends AbstractEntity {

    @OneToOne(mappedBy = "cart")
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @Column(name = "total_price", nullable = false)
    private Double totalPrice = 0D;

    @PrePersist
    private void onCreate() {
        super.setDateOfLastUpdate(LocalDateTime.now());
    }

    @PreUpdate
    private void onUpdate() {
        super.setDateOfLastUpdate(LocalDateTime.now());
//        this.calculatePrice();
    }

//    private void calculatePrice() {
//        this.totalPrice = this.cartItems.stream()
//                .map(CartItem::getFlowerLengthCost)
//                .mapToDouble(FlowerLengthCost::getPrice)
//                .sum();
//    }


}