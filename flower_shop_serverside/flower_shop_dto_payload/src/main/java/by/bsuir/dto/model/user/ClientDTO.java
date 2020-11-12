package by.bsuir.dto.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;


@Getter
@Setter
@NoArgsConstructor
public class ClientDTO extends AbstractUserDTO {

    private String uniqueId;//для понтов типо униклаьный ид хз зачем пригодиться
}
