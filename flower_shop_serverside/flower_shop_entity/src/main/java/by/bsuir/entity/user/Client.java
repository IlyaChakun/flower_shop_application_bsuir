package by.bsuir.entity.user;

import by.bsuir.entity.basket.Basket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class Client extends AbstractUser {

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private Basket basket;

    @Column(name = "unique_id")
    private String uniqueId;//для понтов типо униклаьный ид хз зачем пригодиться
}
