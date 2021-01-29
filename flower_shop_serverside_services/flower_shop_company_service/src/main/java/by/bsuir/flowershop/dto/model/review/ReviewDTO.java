package by.bsuir.flowershop.dto.model.review;

import by.bsuir.flowershop.dto.model.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO extends AbstractDTO {

    private String companyName;

    @NotBlank(message = "Reviewer name must be set")
    private String name;

    @NotBlank(message = "Reviewer name must be set")
    private String phoneNumber;

    @Email(message = "Email must be correct")
    private String email;

    @Length(min = 0, max = 512, message = "Text must not be bigger then 512 symbols")
    private String text;

    @Min(value = 0, message = "Rating can`t be smaller than 0")
    @Max(value = 5, message = "Rating can`t be bigger than 5")
    private Integer rating;

}
