package by.bsuir.entity.cart;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.product.AbstractFlowerProduct;
import by.bsuir.entity.user.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "carts")
@NoArgsConstructor
@Getter
@Setter
public class Cart extends AbstractEntity {

    @OneToOne(mappedBy = "cart")
    private Client client;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "cart_items",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<CartItem> cartItems = new ArrayList<>();


    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice = BigDecimal.ZERO;


    @PrePersist
    private void onCreate() {
        super.setDateOfLastUpdate(LocalDateTime.now());
    }

    @PreUpdate
    private void onUpdate() {
        super.setDateOfLastUpdate(LocalDateTime.now());
    }


}