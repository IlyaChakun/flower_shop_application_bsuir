package by.bsuir.dto.model.order.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class OrderFloristInfoDTO {

    private Long orderId;

    /**
     * chose florist
     **/
    @NotNull(message = "Florist id is required")
    private Long floristId;

    @Null(message = "Florist appointment time will be set automatically")
    private LocalDateTime floristAppointmentTime;

    /**
     * completed by florist
     **/
    @Size(min = 0, max = 512, message = "Comment can`t be bigger than 512")
    private String floristComment;

    @Null(message = "Florist completion time will be set automatically")
    private LocalDateTime floristCompletionTime;
}
