package by.bsuir.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "reviews")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review {

    @Column(name = "review_text")
    private String reviewText;
}
