package by.bsuir.entity.order;

import by.bsuir.entity.BaseAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders_florists")
@Getter
@Setter
@NoArgsConstructor
public class OrderFloristInfo extends BaseAbstractEntity {

    @Column(name = "order_id")
    private Long orderId;

    /**
     * chose florist
     **/
    @Column(name = "florist_id")
    private Long floristId;

    @Column(name = "florist_appointment_time")
    private LocalDateTime floristAppointmentTime;

    /**
     * completed by florist
     **/
    @Column(name = "florist_comment")
    private String floristComment;

    @Column(name = "florist_completion_time")
    private LocalDateTime floristCompletionTime;

    @PreUpdate
    private void resolveAppointmentTime(){
        /** florist was set **/
        if(Objects.nonNull(floristId)){
            this.setFloristAppointmentTime(LocalDateTime.now());
        }
        /** florist completed order **/
        if(Objects.nonNull(floristComment)){
            this.setFloristCompletionTime(LocalDateTime.now());
        }
    }

}
