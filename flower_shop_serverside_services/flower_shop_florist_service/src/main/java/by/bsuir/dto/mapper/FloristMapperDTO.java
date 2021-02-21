package by.bsuir.dto.mapper;


import by.bsuir.dto.FloristDTO;
import by.bsuir.dto.mapper.core.AbstractMapperDTO;
import by.bsuir.entity.Florist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FloristMapperDTO extends AbstractMapperDTO<Florist, FloristDTO> {

    @Autowired
    public FloristMapperDTO() {
        super(Florist.class, FloristDTO.class);
    }
}