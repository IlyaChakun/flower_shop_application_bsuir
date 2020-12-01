package by.bsuir.entity.cart;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.user.Client;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    }

    @Column(name = "shop_id")
    private Long shopId;

}