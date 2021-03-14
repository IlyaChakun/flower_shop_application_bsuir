package by.bsuir.entity.florist;


import by.bsuir.entity.AbstractEntity;
import by.bsuir.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "florists")
@NoArgsConstructor
@Getter
@Setter
public class Florist extends AbstractEntity {

    @Column(name = "shop_id")
    private Long shopId;

//    @Column(name = "user_id")
//    private Long userId;

    @OneToOne(cascade = {CascadeType.ALL})
    private User user;

    @Column(name = "experience")
    private Double experience;

    ////
    @Column(name = "salary")
    private Double salary;//оклад



}
