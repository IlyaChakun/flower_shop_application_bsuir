package by.bsuir.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "florists")
@NoArgsConstructor
@Getter
@Setter
public class Florist extends AbstractEntity {

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "experience")
    private Double experience;

}
