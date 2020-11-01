package by.bsuir.entity.deliveryroute;

import by.bsuir.entity.BaseAbstractEntity;
import by.bsuir.entity.order.Order;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "delivery_route_segments")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DeliveryRouteSegment extends BaseAbstractEntity {

    @ManyToOne
    private Order originOrder;

    @ManyToOne
    private Order destinationOrder;

    @Column(name = "segment_number", nullable = false)
    private String segmentNumber;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "distance", nullable = false)
    private Double distance;

    @Column(name = "distance_unit")
    private String distanceUnit;// km

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "route_time_in_seconds")
    private Long routeTimeInSeconds;

    @Column(name = "is_origin_checked")
    private Boolean isOriginChecked;// отбивается водителем

    @Column(name = "is_destination_checked")
    private Boolean isDestinationChecked;// отбивается водителем

    @Column(name = "origin_visited_time")
    private LocalDateTime originVisitedTime;//время отбива

    @Column(name = "destination_visited_time")
    private LocalDateTime destinationVisitedTime;//время отбива


    @PrePersist
    protected void segmentPrePersist() {
        isDestinationChecked = false;
    }

    @PreUpdate
    protected void segmentPreUpdate() {
        //destinationVisitedTime
        if (Objects.nonNull(isDestinationChecked) && isDestinationChecked) {
            destinationVisitedTime = LocalDateTime.now();
        }
        //originVisitedTime
        if (Objects.nonNull(isOriginChecked) && isOriginChecked) {
            originVisitedTime = LocalDateTime.now();
        }
    }

}
