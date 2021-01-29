package by.bsuir.flowershop.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractDTO extends BaseAbstractDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH.mm")
    private LocalDateTime dateOfCreation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH.mm")
    private LocalDateTime dateOfLastUpdate;

    private String uniqueId;//для понтов типо униклаьный ид хз зачем пригодиться
}
