package by.bsuir.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "payment_types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentType extends AbstractEntity{

    @Column(name = "type", nullable = false)
    private String type;
}
