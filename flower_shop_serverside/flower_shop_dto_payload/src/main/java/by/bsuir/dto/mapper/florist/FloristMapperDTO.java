package by.bsuir.dto.mapper.florist;


import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.dto.model.florist.FloristDTO;
import by.bsuir.entity.florist.Florist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FloristMapperDTO extends AbstractMapperDTO<Florist, FloristDTO> {

    @Autowired
    public FloristMapperDTO() {
        super(Florist.class, FloristDTO.class);
    }
}