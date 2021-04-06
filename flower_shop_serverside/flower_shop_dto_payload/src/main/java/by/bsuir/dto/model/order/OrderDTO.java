package by.bsuir.dto.model.order;

import by.bsuir.dto.model.AbstractDTO;
import by.bsuir.dto.model.order.common.OrderFloristInfoDTO;
import by.bsuir.dto.model.order.common.OrderPriceInfoDTO;
import by.bsuir.dto.model.order.common.OrderProductDTO;
import by.bsuir.dto.model.order.delivery.OrderDeliveryInfoDTO;
import by.bsuir.dto.model.order.review.OrderReviewDTO;
import by.bsuir.entity.order.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

///**
// * This feature of jackson provides ability to deserialize json object according with id real type.
// */
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = BuyNowOrderDTO.class, name = "BuyNowOrder"),
//
//        @JsonSubTypes.Type(value = UsualOrderDTO.class, name = "UsualOrder")}
//)
public class OrderDTO extends AbstractDTO {

    //@NotNull(message = "Client id is required")
    private Long clientId;

    @Null(message = "Order status can`t be set! This will be done automatically")
    private OrderStatus orderStatus;

    private List<OrderProductDTO> orderProducts = new ArrayList<>();

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
