package by.bsuir.entity.order;

import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.product.AbstractFlowerProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "order_products")
@NoArgsConstructor
@Getter
@Setter
public class OrderProduct extends AbstractEntity {

//    @Column(name = "unique_id")
//    private String uniqueId;//для понтов типо униклаьный ид хз зачем пригодиться

//    @Column(name = "comment", length = 512)
//    private String comment;

    @OneToOne
    private AbstractFlowerProduct product;

    @Column(name = "quantity")
    private Integer quantity;

}
