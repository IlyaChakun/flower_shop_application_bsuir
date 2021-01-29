package by.bsuir.flowershop.entity.review;

import by.bsuir.flowershop.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class Review extends AbstractEntity {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "text")
    private String text;

    @Column(name = "rating")
    private Integer rating;

}