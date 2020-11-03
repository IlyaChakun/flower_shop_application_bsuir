package by.bsuir.dto.mapper.core;

import by.bsuir.dto.model.BaseAbstractDTO;
import by.bsuir.entity.BaseAbstractEntity;

import java.util.List;

public interface MapperDTO<E extends BaseAbstractEntity, D extends BaseAbstractDTO> {

    E toEntity(final D d);

    D toDto(final E e);

    List<D> toDtoList(final List<E> eList);
}