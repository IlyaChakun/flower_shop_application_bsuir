package by.bsuir.entity.basket;

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
import java.util.List;

@Entity
@Table(name = "baskets")
@NoArgsConstructor
@Getter
@Setter
public class Basket extends AbstractEntity {

    @OneToOne(mappedBy = "basket")
    private Client client;


    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "basket_products",
            joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<AbstractFlowerProduct> basketProducts = new ArrayList<>();

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