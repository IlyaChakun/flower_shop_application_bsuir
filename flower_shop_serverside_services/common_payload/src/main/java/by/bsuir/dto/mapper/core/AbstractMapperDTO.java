package by.bsuir.dto.mapper.core;


import by.bsuir.dto.model.BaseAbstractDTO;
import by.bsuir.entity.BaseAbstractEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractMapperDTO<E extends BaseAbstractEntity, D extends BaseAbstractDTO>
        implements MapperDTO<E, D> {

    private final Class<E> entityClass;
    private final Class<D> dtoClass;
    @Autowired
    private ModelMapper mapper;

    @Override
    public E toEntity(final D dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public D toDto(final E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }

    @Override
    public List<D> toDtoList(final List<E> products) {
        return
                products.stream()
                        .map(this::toDto)
                        .collect(Collectors.toList());
    }

    Converter<E, D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(final E source, final D destination) {
    }

    void mapSpecificFields(final D source, final E destination) {
    }
}