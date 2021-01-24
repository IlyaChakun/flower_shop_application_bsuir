package by.bsuir.dto.model.order.partial;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class OrderFloristCompletionDTO {

    /**
     * completed by florist
     **/
    @Size(min = 0, max = 512, message = "Comment can`t be bigger than 512")
    private String floristComment;

}
