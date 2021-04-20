package by.bsuir.dto.model.order;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.order.common.OrderFloristInfoDTO;
import by.bsuir.dto.model.order.common.OrderPriceInfoDTO;
import by.bsuir.dto.model.order.common.OrderProductDetailDTO;
import by.bsuir.dto.model.order.delivery.OrderDeliveryInfoDTO;
import by.bsuir.dto.model.order.review.OrderReviewDTO;
import by.bsuir.dto.model.user.UserDTO;
import by.bsuir.entity.order.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)


public class OrderDetailDTO extends AbstractDTO {

    private UserDTO client;

    @Null(message = "Order status can`t be set! This will be done automatically")
    private OrderStatus orderStatus;

    @Null(message = "only if close")
    private String closeDescription;

    private List<OrderProductDetailDTO> products = new ArrayList<>();

    @Size(max = 512, message = "Comment can`t be bigger than 512")
    private String comment;

    /**
     * price\discount info
     */
    @NotNull(message = "Order price info required")
    private OrderPriceInfoDTO orderPriceInfo; // calculated


    /**
     * delivery info
     **/
    @Valid
    @NotNull(message = "Delivery info must be set!")
    private OrderDeliveryInfoDTO orderDeliveryInfo;

    /**
     * order detail info
     */
    @Null(message = "Florist will be chose by admin!")
    private OrderFloristInfoDTO orderFloristInfo;


    @Null(message = "Order review will be done after completion!")
    private OrderReviewDTO orderReview;

}
