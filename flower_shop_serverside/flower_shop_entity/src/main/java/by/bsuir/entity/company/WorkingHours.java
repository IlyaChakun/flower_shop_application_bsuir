package by.bsuir.entity.company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class WorkingHours {

    @Column(name = "hours", nullable = false, length = 15)
    private String hours;

}
